package com.example.factory;

/**
 * @ClassName Factory
 * @Description TODO
 * @Auther Shui Junyang
 * @Date 2022/5/27 下午2:14
 * @Version 1.0
 */
public class Factory {

    public static Product Manufacture(String productName) {
        switch (productName) {
            case "ProductA":
                return new ProductBean.ProductA();
            case "ProductB":
                return new ProductBean.ProductB();
            case "ProductC":
                return new ProductBean.ProductC();
            case "ProductD":
                return new ProductBean.ProductD();
            default:
                return null;
        }
    }
}
