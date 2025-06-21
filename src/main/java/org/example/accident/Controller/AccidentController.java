package org.example.accident.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.accident.Entity.Accident;
import org.example.accident.Entity.Accident_Cause;
import org.example.accident.Entity.Cause;
import org.example.accident.Mapper.AccidentMapper;
import org.example.accident.Service.AccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/*
@RestController
@RequestMapping("/Accident")
public class AccidentController {
    @Autowired
    private AccidentMapper accidentMapper;

    @RequestMapping("/selectAll")
    public List<Accident> selectAll() {
        QueryWrapper<Accident> queryWrapper = new QueryWrapper<>();
        return accidentMapper.selectList(queryWrapper);
    }
    @PostMapping("/insert")
    public Map<String, Object> insert(@RequestBody Accident accident) {
        Map<String, Object> response = new HashMap<>();

        int rows = accidentMapper.insert(accident);
        if (rows > 0) {
            // MyBatis-Plus会自动将自增ID设置到实体对象中
            response.put("message", "插入accident成功");
            response.put("accidentId", accident.getAccidentID());
            return response;
        } else {
            response.put("message", "插入accident失败");
            return response;
        }
    }

    @PostMapping("/update")
    public String update(@RequestBody Accident accident) {
        int rows=accidentMapper.updateById(accident);
        if(rows>0){
            return "修改accident成功";
        }
        else
        {
            return "修改accident失败";
        }
    }

}
*/
@RestController
@RequestMapping("/Accident")
public class AccidentController {
    @Autowired
    private AccidentMapper accidentMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String ACCIDENT_CACHE_KEY = "accident:all";
    private static final String ACCIDENT_SINGLE_CACHE_KEY_PREFIX = "accident:";

    @RequestMapping("/selectAll")
    public List<Accident> selectAll() {
        // 1. 先查缓存
        List<Accident> cachedAccidents = (List<Accident>) redisTemplate.opsForValue().get(ACCIDENT_CACHE_KEY);
        if (cachedAccidents != null) {
            System.out.println("从缓存获取数据");
            return cachedAccidents;
        }

        // 2. 缓存没有，查数据库
        System.out.println("从数据库查询数据");
        QueryWrapper<Accident> queryWrapper = new QueryWrapper<>();
        List<Accident> accidents = accidentMapper.selectList(queryWrapper);

        // 3. 存入 Redis
        System.out.println("存入 Redis");
        redisTemplate.opsForValue().set(ACCIDENT_CACHE_KEY, accidents, 1, TimeUnit.HOURS);

        return accidents;
    }

    @PostMapping("/insert")
    public Map<String, Object> insert(@RequestBody Accident accident) {
        Map<String, Object> response = new HashMap<>();

        int rows = accidentMapper.insert(accident);
        if (rows > 0) {
            // 清除所有事故缓存，因为数据已变更
            redisTemplate.delete(ACCIDENT_CACHE_KEY);

            response.put("message", "插入accident成功");
            response.put("accidentId", accident.getAccidentID());
            return response;
        } else {
            response.put("message", "插入accident失败");
            return response;
        }
    }

    @PostMapping("/update")
    public String update(@RequestBody Accident accident) {
        int rows = accidentMapper.updateById(accident);
        if (rows > 0) {
            // 清除相关缓存
            redisTemplate.delete(ACCIDENT_CACHE_KEY);
            redisTemplate.delete(ACCIDENT_SINGLE_CACHE_KEY_PREFIX + accident.getAccidentID());

            return "修改accident成功";
        } else {
            return "修改accident失败";
        }
    }

    @GetMapping("/{id}")
    public Accident getById(@PathVariable Integer id) {
        String cacheKey = ACCIDENT_SINGLE_CACHE_KEY_PREFIX + id;

        // 先从缓存获取
        Accident accident = (Accident) redisTemplate.opsForValue().get(cacheKey);
        if (accident != null) {
            return accident;
        }

        // 缓存中没有则查询数据库
        accident = accidentMapper.selectById(id);
        if (accident != null) {
            // 存入缓存，设置过期时间30分钟
            redisTemplate.opsForValue().set(cacheKey, accident, 30, TimeUnit.MINUTES);
        }

        return accident;
    }

    @GetMapping("/testRedis")
    public String testRedis() {
        try {
            redisTemplate.opsForValue().set("testKey", "Hello Redis");
            Object value = redisTemplate.opsForValue().get("testKey");
            return "Redis连接成功，测试值: " + value;
        } catch (Exception e) {
            return "Redis连接失败: " + e.getMessage();
        }
    }
}