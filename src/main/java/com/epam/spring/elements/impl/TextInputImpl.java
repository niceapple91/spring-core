package com.epam.spring.elements.impl;

import com.epam.spring.annotations.Entity;
import com.epam.spring.elements.TextInput;

@Entity
public class TextInputImpl implements TextInput {

    public void sendKeys(String text) {
        System.out.println("TextInput is " + text);
    }

    public String getText() {
        return "this is textInput";
    }
}
