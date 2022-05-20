package com.example.annotation.test;

import com.example.annotation.anno.Pro;

import java.lang.reflect.Method;

/**
 * @ClassName DemoReflect
 * @Description TODO
 * @Auther Shui Junyang
 * @Date 2022/5/20 下午2:09
 * @Version 1.0
 */
@Pro(className = "com.example.annotation.domain.Demo1", methodName = "show")
public class DemoReflect {

    public static void main(String[] args) throws Exception {
        //解析注解
        //获取字节码
        Class<DemoReflect> demoReflectClass = DemoReflect.class;

        //获取注解对象
        //其实就是在内存中生成了一个该注解接口的子类实现对象
        Pro an = demoReflectClass.getAnnotation(Pro.class);

        //调用注解对象中定义的抽象方法,获取返回值
        String className = an.className();
        String methodName = an.methodName();
//        System.out.println(className + "------" + methodName);
        Class<?> cls = Class.forName(className);//字节码文件
        Object obj = cls.newInstance();
        Method show = cls.getMethod(methodName);
        show.invoke(obj);
    }
}
