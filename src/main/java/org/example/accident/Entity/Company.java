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
@TableName(value = "Company")
public class Company {
    @TableId(value = "CompanyID",type = IdType.AUTO)
    private Integer CompanyID;
    @TableField(value = "CompanyName")
    private String PersonType;
    @TableField(value = "CompanyRegion")
    private String CompanyRegion;
    @TableField(exist = false)
    private List<Person> Persons;
}
