package www.mapper;


import com.atguigu.www.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author daponi
* @description 针对表【t_emp】的数据库操作Mapper
* @createDate 2022-12-04 23:09:42
* @Entity com.atguigu.www.pojo.Emp
*/
public interface EmpMapper {

    /**
     * 根据id查询员工信息
     */
    List<Emp> getEmpByEmpId(@Param("id")Integer id);

    /**
     * 获取员工以及所对应的部门信息
     */
    List<Emp> getEmpAndDeptByEmpId(@Param("id")Integer id);

    /**
     * 通过分步查询查询员工以及所对应的部门信息的第一步
     */
    List<Emp> getEmpAneDeptByEmpIDByStep(@Param("id")Integer id);

    List<Emp> getDeptAndEmpByStepTwo(@Param("deptId")Integer deptId);
}




