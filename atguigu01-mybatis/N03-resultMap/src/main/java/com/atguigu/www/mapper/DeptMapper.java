package com.atguigu.www.mapper;


import com.atguigu.www.pojo.Dept;
import org.apache.ibatis.annotations.Param;

/**
* @author daponi
* @description 针对表【t_dept】的数据库操作Mapper
* @createDate 2022-12-04 23:04:56
* @Entity com.atguigu.www.pojo.Dept
*/
public interface DeptMapper {

    /**
     * 通过分步查询查询员工以及所对应的部门信息的第二步
     * @return
     */
    Dept getByDeptId(@Param("deptId")Integer id);

    /**
     * 查询部门以及部门中的员工信息
     * @param deptId
     * @return
     */
    Dept getDeptAndEmpByDeptId(@Param("deptId") Integer deptId);

    /**
     * 通过分步查询查询部门以及部门中的员工信息的第一步
     * @param deptId
     * @return
     */
    Dept getDeptAndEmpByStepOne(@Param("deptId") Integer deptId);
}




