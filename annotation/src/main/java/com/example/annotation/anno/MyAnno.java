package com.example.annotation.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解：
 *  接口中抽象方法的要求：
 *      属性的返回值类型：
 *          1.基本数据类型
 *          2.String
 *          3.枚举
 *          4.以上类型的数组
 *
 * 元注解:
 *      "@Target":描述注解能够作用的位置
 *      "@Retention":描述注解被保留的阶段(三个阶段)
 *      "@Documented":描述注解是否支持被抽取至API
 *      "@Inherited":描述注解是否被子类继承
 *
 * @ClassName MyAnno
 * @Description TODO 自定义注解
 * @Auther Shui Junyang
 * @Date 2022/5/20 下午1:23
 * @Version 1.0
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)//自定义注解用此值,表示该注解会被保留到Class字节码文件中,并被JVM读取
@Documented
public @interface MyAnno {

    //该抽象方法就是注解的属性
    String name() default "张三";//若不赋值,则默认张三
    int value();

}
