package com.atguigu.www.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dept implements Serializable {
    private Integer emp_id;

    private String emp_name;

    private static final long serialVersionUID = 1L;
}