package com.atguigu.www.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dept implements Serializable {

    private Integer deptId;

    private String deptName;

    private List<Emp> emps;
}