package com.test.mybatis.pojo;

import java.util.Date;

public class UserDO {

    private int id;
    private String name;
    private int age;
    private String hobbies;
    private Date birthday;

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
