package com.my.test.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user
 */
@TableName(value ="user",autoResultMap = true)
@Data
@NoArgsConstructor
public class User implements Serializable {


    public User(String name, Integer age, String email, Remark remark) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.remark = remark;
    }

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private Remark remark;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    @TableLogic(delval = "id")
    private Long isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}