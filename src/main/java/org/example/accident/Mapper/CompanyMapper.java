package org.example.accident.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.accident.Entity.Company;

@Mapper
public interface CompanyMapper extends BaseMapper<Company> {
}
