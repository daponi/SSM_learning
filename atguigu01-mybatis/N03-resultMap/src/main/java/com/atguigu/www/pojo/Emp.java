package com.atguigu.www.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_emp
 */
@Data
public class Emp implements Serializable {
    /**
     * id
     */
    private Integer emp_id;

    /**
     * 员工姓名
     */
    private String emp_name;

    /**
     * 部门id
     */
    private Integer dept_id;

    /**
     * 员工年龄
     */
    private Integer age;

    /**
     * 员工性别
     */
    private String gender;

    private static final long serialVersionUID = 1L;
}