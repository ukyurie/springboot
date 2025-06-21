package org.example.accident.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Data
@Setter
@Getter
@TableName(value="Accident")
public class Accident {
    @TableId(value ="AccidentID",type = IdType.AUTO)
    private Integer AccidentID;
    @TableField(value="OccurrenceTime")
    private Date OccurrenceTime;
    @TableField(value="OccurrenceLocation")
    private String OccurrenceLocation;
    @TableField(value="AccidentType")
    private String AccidentType;
    @TableField(value="AccidentLevel")
    private String AccidentLevel;

}
