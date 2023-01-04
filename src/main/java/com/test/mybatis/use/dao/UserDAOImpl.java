package com.test.mybatis.use.dao;

import com.test.mybatis.use.pojo.UserDO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserDAOImpl implements UserDAO{

    private SqlSessionFactory sqlSessionFactory;

    public UserDAOImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public UserDO findUserById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDO user = sqlSession.selectOne("test.findUserById",id);
        return user;
    }
}
