package com.atguigu.www.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @TableName t_emp
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Emp implements Serializable {
    /**
     * id
     */
    private Integer empId;

    /**
     * 员工姓名
     */
    private String empName;

    /**
     * 员工年龄
     */
    private Integer age;

    /**
     * 员工性别
     */
    private String gender;

    private Integer deptId;

    private static final long serialVersionUID = 1L;

}