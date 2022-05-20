package com.example.reflect.demo01.demo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @ClassName ReflectFrame
 * @Description TODO 框架类，可以创建任意类的对象，可以执行任意方法
 * @Auther Shui Junyang
 * @Date 2022/5/20 上午10:12
 * @Version 1.0
 */
public class ReflectFrame {

    public static void main(String[] args) throws Exception {

        InputStream inStream=new FileInputStream("/home/ts/AndroidStudioProjects/demo01/reflect/src/main/assets/pro.properties");
        Properties pro=new Properties();

//        ClassLoader classLoader = ReflectFrame.class.getClassLoader();
//        InputStream is = classLoader.getResourceAsStream("pro.properties");

        pro.load(inStream);

        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        Class<?> cls = Class.forName(className);

        Object obj = cls.newInstance();
        Method method = cls.getMethod(methodName,String.class);
        //Method method = cls.getMethod(methodName);

        method.invoke(obj,"西瓜");
        //method.invoke(obj);
    }
}
