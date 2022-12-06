package com.atguigu.www.mapper;


import com.atguigu.www.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author daponi
* @description 针对表【t_emp】的数据库操作Mapper
* @createDate 2022-12-04 23:09:42
* @Entity com.atguigu.www.pojo.Emp
*/
public interface DynamicSQLMapper {

    /**
     * 根据条件查询员工信息
     */
    List<Emp> getEmpByCondition(Emp emp);

    /**
     * 批量增加
     */
    int insertEmpBatch(@Param("emps") List<Emp> emps);

    /**
     * 批量删除
     */
    int deleteEmpBatch(@Param("empIds") Integer[] empIds);

}




