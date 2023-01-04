package com.test.mybatis;

import com.test.mybatis.pojo.UserDO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class TestMybatis {

    public static void main(String[] args) throws IOException {
        //通过配置文件得到一个sqlSessionFactory
        String resource = "sqlMapConfig.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //通过sqlSessionFactory获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDO user = sqlSession.selectOne("test.findUserById",1);
        System.out.println(user);
        System.out.println(user.getBirthday());
        sqlSession.close();

    }
}
