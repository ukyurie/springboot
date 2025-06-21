package org.example.accident.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.accident.Entity.Accident_Cause;
import org.example.accident.Mapper.Accident_CauseMapper;
import org.example.accident.Service.Accident_CauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Accident_CauseServiceImpl extends ServiceImpl<Accident_CauseMapper,Accident_Cause>implements Accident_CauseService   {
    @Autowired
    private Accident_CauseMapper accident_causeMapper;
    @Override
    public List<Accident_Cause> selectAllAccidentWithCause() {
        return  accident_causeMapper.selectAll();
    }
}
