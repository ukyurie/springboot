package org.example.accident.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.example.accident.Entity.Accident;
import org.example.accident.Entity.Accident_Cause;
import org.example.accident.Entity.Cause;

import java.util.List;

@Mapper
public interface Accident_CauseMapper extends BaseMapper<Accident_Cause> {
    @Select("SELECT * FROM Accident_Cause")
    @Results({
            @Result(property = "Accident_CauseID", column = "Accident_CauseID"),
            @Result(property = "AccidentID", column = "AccidentID"),
            @Result(property = "CauseID", column = "CauseID"),
            @Result(property = "Accidents", column = "AccidentID", javaType = List.class, many = @Many(select = "selectAccidentById")),
            @Result(property = "Causes", column = "CauseID", javaType = List.class, many = @Many(select = "selectCauseById"))
    })
    List<Accident_Cause> selectAll();

    @Select("SELECT * FROM Accident WHERE AccidentID = #{accidentID}")
    Accident selectAccidentById(Integer accidentID);

    @Select("SELECT * FROM Cause WHERE CauseID = #{causeID}")
    Cause selectCauseById(Integer causeID);

    @Select("SELECT c.* FROM Cause c JOIN accident_cause ac ON c.causeId = ac.causeId WHERE ac.accidentId = #{accidentId}")
    List<Cause> selectCausesByAccidentId(Integer accidentId);

    @Select("SELECT causeId FROM accident_cause WHERE accidentid = #{accidentId}")
    List<Integer> selectCauseIdsByAccidentId(@Param("accidentId") Integer accidentId);

}
