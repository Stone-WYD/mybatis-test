package com.test.mybatis.use.dao;

import com.test.mybatis.use.pojo.UserDO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class TestMybatisDAO {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        UserDAO userDAO = new UserDAOImpl(sqlSessionFactory);
        UserDO userById = userDAO.findUserById(3);
        System.out.println(userById);
    }
}
