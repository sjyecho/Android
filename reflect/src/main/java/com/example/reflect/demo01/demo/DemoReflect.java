package com.example.reflect.demo01.demo;

import com.example.reflect.demo01.domain.Person;

import java.lang.reflect.Field;

//反射成员变量
public class DemoReflect {

    public static void main(String[] args) throws Exception{

        Class<Person> personClass = Person.class;

        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("----------");

        Person p = new Person();
        Field name = personClass.getField("name");
        name.set(p,"Echo");
        System.out.println(p);

        System.out.println("----------");

        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }

        System.out.println("----------");

        Field age = personClass.getDeclaredField("age");
        /*
            true的值表示反射对象应该在使用时抑制Java语言访问检查。
            false的值表示反映的对象应该强制执行Java语言访问检查
        */
        age.setAccessible(true);
        age.set(p,20);
        System.out.println(p);
    }
}
