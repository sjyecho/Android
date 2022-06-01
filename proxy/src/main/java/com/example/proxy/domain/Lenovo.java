package com.example.proxy.domain;

import com.example.proxy.impl.ISaleComputer;

/**
 * @ClassName Lenovo
 * @Description TODO 被代理的对象
 * @Auther Shui Junyang
 * @Date 2022/5/27 下午4:29
 * @Version 1.0
 */
public class Lenovo implements ISaleComputer {
    @Override
    public String sale(double money) {
        System.out.println("花了" + money + "元钱买了一台电脑");
        return "返回一台电脑";
    }

    @Override
    public void show() {
        System.out.println("展示刚买的联想电脑");
    }
}
