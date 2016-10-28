package com.epam.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvocationHandlerImpl implements InvocationHandler {
    Object proxiedObject;

    public static<T> T newInstance(T obj){
        return (T)Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandlerImpl(obj));
    }

    public InvocationHandlerImpl(Object obj) {
        proxiedObject = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        System.out.println("Before method in class - " + proxiedObject.getClass().getSimpleName() + " in method " + method.getName());
        result = method.invoke(proxiedObject,args);
        System.out.println("After method in class - " + proxiedObject.getClass().getSimpleName() + " in method " + method.getName());
        return result;
    }
}
