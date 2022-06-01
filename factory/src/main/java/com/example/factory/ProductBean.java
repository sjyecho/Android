package com.example.factory;

/**
 * @ClassName ProductBean
 * @Description TODO
 * @Auther Shui Junyang
 * @Date 2022/5/27 下午2:12
 * @Version 1.0
 */
public class ProductBean {

    static class ProductA extends Product{
        @Override
        public void Show() {
            System.out.println("生产A产品");
        }
    }

    static class ProductB extends Product{
        @Override
        public void Show() {
            System.out.println("生产B产品");
        }
    }

    static class ProductC extends Product{
        @Override
        public void Show() {
            System.out.println("生产C产品");
        }
    }

    static class ProductD extends Product{
        @Override
        public void Show() {
            System.out.println("生产D产品");
        }
    }
}
