package org.example.accident.Controller;

import org.example.accident.Entity.Accident_Cause;
import org.example.accident.Entity.Cause;
import org.example.accident.Mapper.CauseMapper;
import org.example.accident.Service.CauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Cause")
public class CauseController {
    @Autowired
    private CauseMapper causeMapper;


    @PostMapping("/insert")
    public Map<String, Object> insert(@RequestBody Cause cause) {
        Map<String, Object> response = new HashMap<>();
        int rows = causeMapper.insert(cause);
        if (rows > 0) {
            // MyBatis-Plus会自动将自增ID设置到实体对象中
            response.put("message", "插入cause成功");
            response.put("causeId", cause.getCauseID());
            return response;
        } else {
            response.put("message", "插入cause失败");
            return response;
        }
    }

    @PostMapping("/update")
    public String update(@RequestBody Cause cause) {
        int rows=causeMapper.updateById(cause);
        if(rows>0){
            return "修改cause成功";
        }
        else
        {
            return "修改cause失败";
        }
    }


}
