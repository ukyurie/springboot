package org.example.accident.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.accident.Entity.Accident_Cause;
import org.example.accident.Entity.Cause;
import org.example.accident.Mapper.AccidentMapper;
import org.example.accident.Mapper.Accident_CauseMapper;
import org.example.accident.Mapper.CauseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Accident_Cause")
public class Accident_CauseController {
    @Autowired
    private Accident_CauseMapper accident_causeMapper;
    @Autowired
    private AccidentMapper accidentMapper;
    @Autowired
    private CauseMapper causeMapper;

    @RequestMapping("/selectAll")
    public List<Accident_Cause> selectAll() {
        return accident_causeMapper.selectAll();
    }
    @PostMapping("/insert")
    public String insert(@RequestBody Accident_Cause accident_cause) {
        int rows=accident_causeMapper.insert(accident_cause);
        if(rows>0){
            return "插入accident_cause成功";
        }
        else
        {
            return "插入accident_cause失败";
        }
    }

    @GetMapping("/selectByAccidentId/{accidentId}")
    public List<Cause> selectByAccidentId(@PathVariable Integer accidentId) {
        return accident_causeMapper.selectCausesByAccidentId(accidentId);
    }


    @DeleteMapping("/delete/{accidentId}")
    @Transactional(rollbackFor = Exception.class)
    public String deleteAccident(@PathVariable Integer accidentId) {
        try {
            // 1. 获取所有关联的成因ID
            List<Integer> causeIds = accident_causeMapper.selectCauseIdsByAccidentId(accidentId);

            // 2. 删除事故-成因关联关系
            accident_causeMapper.delete(
                    new LambdaQueryWrapper<Accident_Cause>()
                            .eq(Accident_Cause::getAccidentID, accidentId)
            );

            // 3. 删除成因
            if (!causeIds.isEmpty()) {
                causeMapper.deleteByIds(causeIds);
            }

            // 4. 删除事故
            accidentMapper.deleteById(accidentId);

            return "事故及其相关成因删除成功";
        } catch (Exception e) {
            throw new RuntimeException("删除失败：" + e.getMessage());
        }
    }


    @DeleteMapping("/delete/{causeId}/{accidentId}")
    @Transactional(rollbackFor = Exception.class)
    public String deleteCause(@PathVariable Integer causeId, @PathVariable Integer accidentId) {
        try {
            // 1. 删除关联关系
            accident_causeMapper.delete(
                    new LambdaQueryWrapper<Accident_Cause>()
                            .eq(Accident_Cause::getAccidentID, accidentId)
                            .eq(Accident_Cause::getCauseID, causeId)
            );

            // 2. 删除成因
            causeMapper.deleteById(causeId);

            return "成因删除成功";
        } catch (Exception e) {
            throw new RuntimeException("删除失败：" + e.getMessage());
        }
    }

}
