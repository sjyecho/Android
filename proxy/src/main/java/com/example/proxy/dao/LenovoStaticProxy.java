package com.example.proxy.dao;

import com.example.proxy.impl.ISaleComputer;

/**
 * @ClassName StaticProxy
 * @Description TODO 静态代理
 * @Auther Shui Junyang
 * @Date 2022/5/27 下午5:00
 * @Version 1.0
 */
public class LenovoStaticProxy implements ISaleComputer {

    private ISaleComputer iSaleComputer;

    public void setInstance(ISaleComputer interface1) {
        iSaleComputer = interface1;
    }

    @Override
    public String sale(double money) {
        return null;
    }

    @Override
    public void show() {
        System.out.println("show之前...");
        iSaleComputer.show();
        System.out.println("show之后...");
    }
}
