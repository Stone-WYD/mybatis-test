package com.test.mybatis.use.pojo;

import java.util.Date;
import java.util.List;

public class UserDO {

    private int id;
    private String name;
    private int age;
    private String hobbies;
    private Date birthday;

    private List<String> stringList;

    public UserDO(){}

    public UserDO(int id,String name,int age,String hobbies,Date birthday){
        System.out.println("创建了有参UserDo");
        this.id = id;
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", hobbies='" + hobbies + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
