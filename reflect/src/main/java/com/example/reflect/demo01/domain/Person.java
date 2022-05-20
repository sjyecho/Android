package com.example.reflect.demo01.domain;

/**
 * @ClassName Student
 * @Description TODO 一个实体类，用于创建对象
 * @Auther Shui Junyang
 * @Date 2022/5/20 上午10:12
 * @Version 1.0
 */
public class Person {

    public String name;
    private int age;

    public void eat() {
        System.out.println("eat...");
    }

    public void eat(String food) {
        System.out.println("吃" + food);
    }

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
