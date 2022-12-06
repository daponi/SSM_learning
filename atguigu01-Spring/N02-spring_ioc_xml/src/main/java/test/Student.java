package test;

import lombok.NoArgsConstructor;

/**
 * 测试反射
 */
@NoArgsConstructor
public class Student {
    public void smile(){
        System.out.println("Student smlies......");
    }

    public void cry(){
        System.out.println("Student cries......");
    }

    private void angry(){
        System.out.println("Student angry......");
    }

    protected void happy(){
        System.out.println("Student happy......");
    }
}
