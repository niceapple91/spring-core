package map;

import elements.impl.ButtonImpl;
import elements.impl.TextInputImpl;

import java.util.HashMap;

public class ModulesDependencies {

    private static HashMap<String, Object> contextBean = new HashMap<String, Object>();

    private static void init(){
        contextBean.put("Button", new ButtonImpl());
        contextBean.put("TextInput", new TextInputImpl());
    }

}
