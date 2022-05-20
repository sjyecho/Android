package com.example.reflect.demo01.demo;

import com.example.reflect.demo01.domain.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

//反射成员方法
public class DemoReflect3 {

    public static void main(String[] args) throws Exception {

        Class<Person> personClass = Person.class;

        Method eat = personClass.getMethod("eat");
        Person p = new Person();
        Person p1 = new Person();
        eat.invoke(p);

        System.out.println("----------");

        Method eat1 = personClass.getMethod("eat", String.class);
        eat1.invoke(p, "西瓜");

        System.out.println("----------");

        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);//包括Object类中的方法
        }

        System.out.println("----------");

        String name = eat1.getName();
        System.out.println(name);

        System.out.println("----------");

        System.out.println("p.hashCode():" + p.hashCode());
        System.out.println("p1.hashCode():" + p1.hashCode());

        System.out.println("p:" + p);
        System.out.println("p1:" + p1);

    }
}
