package com.atguigu.www.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Person{
    private Integer sId;
    private String sName;
    private String gender;
    private Integer age;
    private Double score;
    private Clazz clazz;
    private String[] hobbies;
    private Map<String,Teacher> teacherMap;
    public Student(Integer sId, String sName, String gender, Integer age) {
        this.sId = sId;
        this.sName = sName;
        this.gender = gender;
        this.age = age;
    }

    public Student(Integer sId, String sName, String gender, Double score) {
        this.sId = sId;
        this.sName = sName;
        this.gender = gender;
        this.score = score;
    }

    public Student(String s, String s2, String s23, String s234, String age, Clazz clazz) {
    }
}
