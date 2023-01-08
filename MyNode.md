# MybatisTest



## 一、 数据库环境

### （1）创建数据库：

```sql
CREATE DATABASE mybatis_test
```

### （2）创建表：

```sql
CREATE TABLE `mybatis_test`.`Untitled`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NULL,
  `age` int NULL,
  `hobbies` varchar(255) NULL,
  `birthday` date NULL,
  PRIMARY KEY (`id`)
);
```

### （3）表中插入数据：

```sql
INSERT INTO `mybatis_test`.`user` (`id`, `name`, `age`, `hobbies`, `birthday`) VALUES (1, '王玉东', 25, '篮球，电子游戏', '1997-09-21');
INSERT INTO `mybatis_test`.`user` (`id`, `name`, `age`, `hobbies`, `birthday`) VALUES (2, '小明', 24, '足球', '1998-06-04');
INSERT INTO `mybatis_test`.`user` (`id`, `name`, `age`, `hobbies`, `birthday`) VALUES (3, '小白', 22, '网球', '2000-02-04');
```

## 二、  JDBC连接数据库

### （1）流程

1. 加载数据库驱动
2. 从硬编码的String中获取信息（url，username，password）建立数据库连接（connection）
3. 定义sql语句（可以用？设置占位符）
4. 通过connection获取预处理后的statement
5. statement设置sql中的参数
6. 通过statement向数据库发起sql查询，获取结果集resultSet
7. 通过结果集可获取数据库数据（getString()方法）
8. 关闭resultSet，statement，connection（与打开顺序相反）

## 三、Mybatis的使用

### （1）mybatis基本流程，不含动态代理

1. 配置一个mybatis配置文件（sqlMapConfig.xml），其中确定了连接类型（JDBC），连接驱动，url，username，password
2. mybatis配置文件可关联一些mapper文件，mapper文件中配置了该mapper的命名空间（namespace），sql语句，入参出参类型
3. 将mybatis配置文件里的信息收集到inputStream中，通过SqlSessionFactoryBuilder（建造者模式）获取inputStream中的数据封装成sqlSessionFactory
4. 通过sqlSessionFactory获取sqlSession
5. 使用sqlSession执行某命名空间中的sql语句，直接获取到出参类型（UserDO）的实体对象

### （2）mybatis的DAO开发

1. 对每个mapper文件写一个接口
2. mapper文件中的一个sql对应接口中的一个方法
3. 写接口实现类，在接口的实现类中，需要注入SqlSessionFactory，使用SqlSessionFactory的openSession方法获取sqlSession来完成每个接口方法

### （3）mybatis动态代理

1. 为每个mapper文件写一个同名的接口，mapper文件的命名空间写入同名接口的项目地址
2. 同名方法的接口名为对应mapper文件sql的id
3. 在测试方法中，获取到sqlSession后，通过其getMapper方法，传入Mapper的class，即可获取到mybatis动态代理生成的实体对象

## 四、Mybatis技术内幕

### （1）Mybatis整体架构

![](.\nodePictures\Mybatis.png)

### （2）基础支持模块

#### 1.解析器模块思维导图
![](.\nodePictures\xpath\XPathParser.jpg)

#### 2.反射模块

