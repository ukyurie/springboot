package org.example.accident.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@TableName(value = "Person")
public class Person {
    @TableId(value = "PersonID",type = IdType.AUTO)
    private Integer PersonID;
    @TableField(value = "PersonType")
    private String PersonType;
    @TableField(value = "CompanyID")
    private Integer CompanyID;
    @TableField(exist = false)
    private Company Company;
}
