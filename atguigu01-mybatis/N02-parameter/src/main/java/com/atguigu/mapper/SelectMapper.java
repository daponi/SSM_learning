package com.atguigu.mapper;

import com.atguigu.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 1.若sql语句查询的结果为多条时，一定不能以实体类类型作为方法的返回值
 * 否则会抛出异常TooManyResultsException
 *
 * 2.若sql语句查询的结果为1条时，此时可以使用实体类类型或list集合类型作为方法的返回值
 *
 * 3.根据用户id查询用户信息为map集合，map集合会以数据库里的字段名为键key，字段值为值value
 */
public interface SelectMapper {

    User selectOneById(@Param("id")String id);

    List<User> selectAll();

    Integer selectCount();

    Map<String,Object> selectOneByIdToMap(@Param("id")String id);

    List<Map<String,Object>> selectAllUserToListMap();

    @MapKey("id")
    Map<String,Object> selectAllUserToMap();


}
