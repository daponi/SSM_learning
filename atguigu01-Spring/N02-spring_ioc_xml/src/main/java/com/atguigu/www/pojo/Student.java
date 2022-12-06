package com.atguigu.www.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Person{
    private Integer sId;
    private String sName;
    private String gender;
    private Integer age;
}
