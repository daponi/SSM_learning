package com.atguigu.mapper;

import com.atguigu.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 1.模糊查询like的三种办法：
 * 1. like ‘%${xxx}%’ ，但有SQL注入的风险
 * 2. like concat(‘%’,#{xxx},‘%’)，使用Mysql/Oracle内置的字符串拼接函数，但项目更换数据库时可能出现问题，且不会走索引
 * 3. like “%” #{xxx} “%” ，推荐使用
 *
 * 2.批量删除in不能用#{xxx},用${xxx}
 * 或者替换成mybatis框架自带的foreach循环
 *
 * 3.添加功能获取自增的主键,在xxxmapper.xml文件中设置：
 *  	useGeneratedKeys：设置使用自增的主键
 *  	keyProperty：因为增删改有统一的返回值是受影响的行数，因此只能将获取的自增的主键放在传输的参数user对象的某个属性中
 */
public interface SpecialSqlMapper {
    List<User> selectByLike(@Param("username") String username);

    int deleteBatch(@Param("ids") String id);

    int insertUserAndGetId(User user);

}