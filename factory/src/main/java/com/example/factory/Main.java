package com.example.factory;

/**
 * @ClassName Main
 * @Description TODO 工厂模式创建对象
 * @Auther Shui Junyang
 * @Date 2022/5/27 下午2:46
 * @Version 1.0
 */
public class Main {

    public static void main(String[] args) {

        try {
            Factory.Manufacture("ProductA").Show();
        } catch (Exception e) {
            System.out.println("没有这种产品");
        }
    }
}

