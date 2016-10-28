package elements.impl;

import elements.Button;

public class ButtonImpl implements Button {
    public void click() {
        System.out.println("Button click");
    }

    public String getText() {
        return "this is button";
    }
}
