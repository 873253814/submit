package com.example.demo.listen;

import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class EventProxy<L> {
    private L proxy;

    private Class<L> aClass;

    private L bean;

    public EventProxy(Class<L> aClass, L bean) {
        this.aClass = aClass;
        this.bean = bean;
        proxy = aClass.cast(Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{aClass}, new EventProxyHandler()));
    }

    public void add(Class<?> clazz) {

    }


    public EventListener.IEvent getProxy() {
        return (EventListener.IEvent)proxy;
    }

    private class EventProxyHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                return method.invoke(bean, args);
            } catch (InvocationTargetException e){
                throw e.getCause();
            }
        }
    }
}
