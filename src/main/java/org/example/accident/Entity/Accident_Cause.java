package org.example.accident.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
@TableName(value = "Accident_Cause")
public class Accident_Cause {
    @TableId(type = IdType.AUTO)
    private Integer Accident_CauseID;

    @TableField(value = "AccidentID")
    private Integer AccidentID;

    @TableField(value = "CauseID")
    private Integer CauseID;

    @TableField(exist = false)
    private List<Accident> Accidents;
    @TableField(exist = false)
    private List<Cause> Causes;
}
