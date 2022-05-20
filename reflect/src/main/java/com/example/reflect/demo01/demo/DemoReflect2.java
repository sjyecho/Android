package com.example.reflect.demo01.demo;

import com.example.reflect.demo01.domain.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

//反射构造器
public class DemoReflect2 {

    public static void main(String[] args) throws Exception {

        Class<Person> personClass = Person.class;

        Constructor<Person> constructor = personClass.getConstructor(String.class, int.class);
        System.out.println(constructor);

        System.out.println("----------");

        //创建对象
        Person person = constructor.newInstance("张三", 21);
        System.out.println(person);

        System.out.println("----------");

        Person person1 = personClass.newInstance();
        System.out.println(person1);

        System.out.println("----------");


    }
}
