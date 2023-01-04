package com.test.jdbc;

import java.sql.*;

public class TestJdbc {
    public static void main(String[] args) throws SQLException {
        Connection  connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //加载数据库驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //通过驱动管理类获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8888/mybatis_test?characterEncoding=utf-8", "root", "123456");
            //定义sql语句，？表示占位符
            String sql = "select * from user where name = ?";
            //获取预处理的statement
            preparedStatement = connection.prepareStatement(sql);
            //设置参数，第一个参数为sql语句中参数的序号（1开始），第二个参数为设置的参数值
            preparedStatement.setString(1,"王玉东");
            //向数据库发出sql查询，查询出结果集
            resultSet = preparedStatement.executeQuery();
            //遍历查询结果集
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + " "
                        + resultSet.getString("age") + " "
                        + resultSet.getString("hobbies") );
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            //释放资源
            if (resultSet != null){
                resultSet.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }
}
