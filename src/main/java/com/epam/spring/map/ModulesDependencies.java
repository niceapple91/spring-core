package com.epam.spring.map;

import com.epam.spring.annotations.Entity;
import com.epam.spring.annotations.Inject;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

public class ModulesDependencies {

    private static HashMap<Class, Class> contextBean = new HashMap<Class, Class>();

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
        try {
            T object = (T)contextBean.get(clazz).getDeclaredConstructor().newInstance();
            injecting(object);
            return object;
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

}
