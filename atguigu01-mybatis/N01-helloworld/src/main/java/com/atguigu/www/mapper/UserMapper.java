package com.atguigu.www.mapper;

import com.atguigu.www.pojo.User;

import java.util.List;

/**
* @author daponi
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2022-12-03 01:25:59
* @Entity com.atguigu.www.pojo.User
*/
public interface UserMapper {

    int insertUser();

    int updateUser();

    int deleteUser();

    User getOneUser();

    List<User> getAllUser();


    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
