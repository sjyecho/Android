package com.example.proxy.test;

import com.example.proxy.domain.Lenovo;
import com.example.proxy.factory.ProxyFactory;
import com.example.proxy.impl.ISaleComputer;

/**
 * @ClassName DynamicProxyTest
 * @Description TODO 使用工厂创建动态代理对象
 * @Auther Shui Junyang
 * @Date 2022/5/30 上午9:22
 * @Version 1.0
 */
public class DynamicProxyTest {

    public static void main(String[] args) {

        Lenovo lenovo=new Lenovo();

        ProxyFactory proxyFactory=new ProxyFactory(lenovo);
        ISaleComputer iSaleComputer = proxyFactory.create(ISaleComputer.class);

        iSaleComputer.show();
    }
}
