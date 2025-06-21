package org.example.accident.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.accident.Entity.Accident_Cause;
import org.example.accident.Mapper.Accident_CauseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public interface Accident_CauseService extends IService<Accident_Cause> {
    public List<Accident_Cause> selectAllAccidentWithCause();

}
