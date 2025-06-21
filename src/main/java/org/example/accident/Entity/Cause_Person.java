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
@TableName(value="Cause_Person")
public class Cause_Person {
    @TableId(value = "Cause_PersonID", type = IdType.AUTO)
    private Integer Cause_PersonID;
    @TableField(value = "CauseID")
    private Integer CauseID;
    @TableField(value = "PersonID")
    private Integer PersonID;

    @TableField(exist = false)
    private List<Cause> Causes;
    @TableField(exist = false)
    private List<Person> Persons;
}
