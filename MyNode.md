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
2. 获取数据库连接（connection）
3. 定义sql语句（可以用？设置占位符）
4. 通过connection获取预处理后的statement
5. statement设置sql中的参数
6. 通过statement向数据库发起sql查询，获取结果集resultSet
7. 通过结果集可获取数据库数据（getString()方法）
8. 关闭resultSet，statement，connection（与打开顺序相反）

## 三、Mybatis连接数据库
