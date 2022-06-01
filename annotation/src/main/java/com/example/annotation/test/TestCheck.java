package com.example.annotation.test;

import com.example.annotation.anno.Check;
import com.example.annotation.domain.Calculator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 简单的测试框架
 * 当主方法执行后,会自动的自行检测所有加注解的方法,判断方法是否有异常并记录到文件中
 *
 * @ClassName TestCheck
 * @Description TODO 使用注解测试简单的框架
 * @Auther Shui Junyang
 * @Date 2022/5/20 下午2:44
 * @Version 1.0
 */
public class TestCheck {

    public static void main(String[] args) throws IOException {
        Calculator c = new Calculator();
        //获取字节码文件对象
        Class<Calculator> cls = Calculator.class;
        //获取所有方法
        Method[] methods = cls.getMethods();
        int number = 0;//异常出现的次数
        BufferedWriter bw = new BufferedWriter(new FileWriter("annotation/bug.txt"));
        for (Method method : methods) {
            //判断方法上是否有指定的Check注解
            if (method.isAnnotationPresent(Check.class)) {
                try {
                    //有注解,执行
                    method.invoke(c);
                    //捕获异常
                } catch (Exception e) {
                    //记录异常到文件中
                    number++;
                    bw.write(method.getName() + "方法出异常了");
                    bw.newLine();
                    bw.write("异常的名称:" + e.getCause().getClass().getSimpleName());
                    bw.newLine();
                    bw.write("异常的原因:" + e.getCause().getMessage());
                    bw.newLine();
                    bw.write("----------");
                    bw.newLine();
                }
            }
        }
        bw.write("本次测试一共出现了" + number + "次异常");
        bw.flush();
        bw.close();

    }
}
