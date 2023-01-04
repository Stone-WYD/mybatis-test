package com.test.mybatis.use.dao;

import com.test.mybatis.use.pojo.UserDO;

public interface UserDAO {
    UserDO findUserById(int id);
}
