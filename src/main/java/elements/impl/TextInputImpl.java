package elements.impl;

import elements.TextInput;

public class TextInputImpl implements TextInput {

    public void sendKeys(String text) {
        System.out.println("TextInput is " + text);
    }

    public String getText() {
        return "this is textInput";
    }
}
