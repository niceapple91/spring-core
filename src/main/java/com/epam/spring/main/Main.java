package com.epam.spring.main;

import com.epam.spring.map.ModulesDependencies;
import com.epam.spring.pages.Page;

public class Main {

    public static void main(String[] args) {
        ModulesDependencies bean = new ModulesDependencies();
        Page page = bean.getClassImpl(Page.class);
        page.search("some new");
    }


}
