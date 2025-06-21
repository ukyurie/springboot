package org.example.accident.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.accident.Entity.Accident;

@Mapper
public interface AccidentMapper extends BaseMapper<Accident> {
}
