package com.example.proxy.dao;

import com.example.proxy.domain.Lenovo;
import com.example.proxy.impl.ISaleComputer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName ProxyTest
 * @Description TODO 动态代理Proxy
 * @Auther Shui Junyang
 * @Date 2022/5/27 下午4:30
 * @Version 1.0
 */
public class DynamicProxy {

    public static void main(String[] args) {

        //创建真实对象
        Lenovo lenovo = new Lenovo();

        //动态代理增强lenovo对象
        /*
            三个参数：
                1.类加载器
                2.接口数组
                3.代理业务逻辑
         */
        ISaleComputer lenovo_proxy = (ISaleComputer) Proxy.newProxyInstance(
                lenovo.getClass().getClassLoader(),
                lenovo.getClass().getInterfaces(),
                new InvocationHandler() {
            /*
                三个参数:
                    1.proxy:代理对象
                    2.method:代理对象调用的方法
                    3.args:代理对象调用方法时,传递的实际参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("sale")) {
                    double money = (double) args[0];
                    money = money * 0.85;
                    String obj = (String) method.invoke(lenovo, money);
                    return obj + "和一个鼠标垫";
                } else {
                    Object obj = method.invoke(lenovo, args);
                    return obj;
                }

            }
        });

        //调用方法
        String str = lenovo_proxy.sale(8000);
        System.out.println(str);
        System.out.println("----------");
        //lenovo_proxy.show();

    }
}
