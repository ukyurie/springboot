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
@TableName(value="Cause")
public class Cause {
    @TableId(value = "CauseID", type = IdType.AUTO)
    private Integer CauseID;
    @TableField(value = "CauseBasicType")
    private String CauseBasicType;
    @TableField(value = "CauseIndirectType")
    private String CauseIndirectType;
    @TableField(value = "CauseDirectType")
    private String CauseDirectType;
    @TableField(value = "CauseDescription")
    private String CauseDescription;
}

