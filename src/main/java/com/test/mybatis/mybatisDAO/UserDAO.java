package com.test.mybatis.mybatisDAO;

import com.test.mybatis.pojo.UserDO;

public interface UserDAO {
    UserDO findUserById(int id);
}
