package com.epam.spring.map;

import com.epam.spring.annotations.*;
import com.epam.spring.proxy.InvocationHandlerImpl;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class ModulesDependencies {

    private static HashMap<Class, Class> contextBean = new HashMap<Class, Class>();
    private static Random rand = new Random();

    public ModulesDependencies() {
        init();
    }

    private void init(){
        Reflections reflections = new Reflections("com.epam.spring");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Entity.class);
        for (Class<?> context : annotated) {
            Class interf = context.getInterfaces()[0];
            contextBean.put(interf, context);
        }

    }

    public <T extends Object> T getClassImpl(Class<T> clazz){
        T instance;
        try {
            T object = (T)contextBean.get(clazz).getDeclaredConstructor().newInstance();
            initting(object);
            injecting(object);
            randoming(object);
            postInitting(object);
            return addProfiling(object);
        } catch (Exception e) {
            return null;
        }
    }

    private <T> void injecting(T object){
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length != 0) {
            for (Field field : fields) {
                if (field.isAnnotationPresent(Inject.class)) {
                    field.setAccessible(true);
                    try {
                        field.set(object, getClassImpl(field.getType()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private <T extends Object> T addProfiling(T object){
        T instance;
        if (isProfilePresent(object)){
            instance = InvocationHandlerImpl.newInstance(object);
        }
        else{
            instance = object;
        }
        return instance;
    }

    private <T> boolean isProfilePresent(T object){
        Class clazz = object.getClass();
        return clazz.isAnnotationPresent(Profiling.class);
    }

    private <T> void initting(T object){
        Class clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        if (methods.length != 0) {
            for (Method method : methods) {
                if (method.isAnnotationPresent(Init.class)) {
                    method.setAccessible(true);
                    try {
                        method.invoke(object);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private <T> void postInitting(T object){
        Class clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        if (methods.length != 0) {
            for (Method method : methods) {
                if (method.isAnnotationPresent(PostInit.class)) {
                    method.setAccessible(true);
                    try {
                        method.invoke(object);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private <T> void randoming(T object){
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length != 0) {
            for (Field field : fields) {
                if (field.isAnnotationPresent(Randome.class)) {
                    field.setAccessible(true);
                    int value = rand.nextInt(field.getAnnotation(Randome.class).max()-field.getAnnotation(Randome.class).min()) + field.getAnnotation(Randome.class).min();
                    try {
                        field.set(object, value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
