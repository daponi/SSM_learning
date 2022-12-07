package com.atguigu.www.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer age;

    public User() {
        System.out.println("生命周期：1、创建对象User()");
    }

    public void setId(Integer id) { System.out.println("生命周期：2、依赖注入setId(Integer id)"); this.id = id;
    }

    public void initMethod(){ System.out.println("生命周期：3、初始化initMethod()");
    }


    public void destroyMethod(){ System.out.println("生命周期：5、销毁destroyMethod()");
    }
}
