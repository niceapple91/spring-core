package map;

import annotations.Inject;
import elements.Button;
import elements.TextInput;
import elements.impl.ButtonImpl;
import elements.impl.TextInputImpl;
import pages.Page;
import pages.impl.PageObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class ModulesDependencies {

    private static HashMap<Class, Class> contextBean = new HashMap<Class, Class>();

    public ModulesDependencies() {
        init();
    }

    private void init(){
        contextBean.put(Button.class, ButtonImpl.class);
        contextBean.put(TextInput.class, TextInputImpl.class);
        contextBean.put(Page.class, PageObject.class);
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
