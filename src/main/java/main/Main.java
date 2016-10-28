package main;

import map.ModulesDependencies;
import pages.Page;
import pages.impl.PageObject;

public class Main {

    public static void main(String[] args) {
        ModulesDependencies bean = new ModulesDependencies();
        Page page = bean.getClassImpl(Page.class);
        page.search("some new");
    }


}
