package com.epam.spring.elements.impl;

import com.epam.spring.annotations.Entity;
import com.epam.spring.elements.Button;

@Entity
public class ButtonImpl implements Button {
    public void click() {
        System.out.println("Button click");
    }

    public String getText() {
        return "this is button";
    }
}
