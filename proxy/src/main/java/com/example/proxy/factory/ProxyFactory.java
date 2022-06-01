package com.example.proxy.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName ProxyFactory
 * @Description TODO 一个可以创建动态代理对象的工厂类
 * @Auther Shui Junyang
 * @Date 2022/5/27 下午5:56
 * @Version 1.0
 */
public class ProxyFactory {

    Object object;

    public ProxyFactory(Object obj) {
        this.object = obj;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(final Class<T> say) {
        return (T) Proxy.newProxyInstance(
                say.getClassLoader(),
                new Class<?>[]{say},
                new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("买电脑前");
                Object result = method.invoke(object, args);
                System.out.println("买电脑后");
                return result;
            }
        });
    }

}
