package com.example.proxy.test;

import com.example.proxy.dao.LenovoStaticProxy;
import com.example.proxy.domain.Lenovo;

/**
 * @ClassName StaticProxyTest
 * @Description TODO 测试静态代理
 * @Auther Shui Junyang
 * @Date 2022/5/27 下午5:04
 * @Version 1.0
 */
public class StaticProxyTest {

    public static void main(String[] args) {

        LenovoStaticProxy lenovoStaticProxy = new LenovoStaticProxy();

        lenovoStaticProxy.setInstance(new Lenovo());
        lenovoStaticProxy.show();
    }
}
