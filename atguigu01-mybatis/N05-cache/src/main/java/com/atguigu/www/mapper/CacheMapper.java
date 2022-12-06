package com.atguigu.www.mapper;

import com.atguigu.www.pojo.Emp;
import org.apache.ibatis.annotations.Param;

public interface CacheMapper {
    Emp getEmpById(@Param("id")Integer id);
}
