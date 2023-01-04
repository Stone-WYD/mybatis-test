package com.test.mybatis.use.proxy;

import com.test.mybatis.use.pojo.UserDO;

public interface UserMapper {
    UserDO findUserById(int id);
}
