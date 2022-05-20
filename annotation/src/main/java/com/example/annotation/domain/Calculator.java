package com.example.annotation.domain;

import com.example.annotation.anno.Check;

/**
 * 模拟一个功能,用自定义注解进行检查
 *
 * @ClassName Calculator
 * @Description TODO
 * @Auther Shui Junyang
 * @Date 2022/5/20 下午2:41
 * @Version 1.0
 */
public class Calculator {

    @Check
    public void add() {
        String str=null;
        str.toString();
        System.out.println("1 + 0 = " + (1 + 0));
    }

    @Check
    public void sub() {
        System.out.println("1 - 0 = " + (1 - 0));
    }

    @Check
    public void mul() {
        System.out.println("1 * 0 = " + (1 * 0));
    }

    @Check
    public void div() {
        System.out.println("1 / 0 = " + (1 / 0));
    }

    @Check
    public void show() {
        System.out.println("永无bug......");
    }
}
