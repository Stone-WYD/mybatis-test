package com.test.mybatis.use.proxy;

import com.test.mybatis.use.pojo.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@Mapper
public interface UserMapper {
    UserDO findUserById(int id);

}
