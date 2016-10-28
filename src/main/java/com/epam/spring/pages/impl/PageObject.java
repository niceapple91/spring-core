package com.epam.spring.pages.impl;

import com.epam.spring.annotations.Entity;
import com.epam.spring.annotations.Inject;
import com.epam.spring.elements.Button;
import com.epam.spring.elements.TextInput;
import com.epam.spring.pages.Page;

@Entity
public class PageObject implements Page{

    @Inject
    private Button button;
    @Inject
    private TextInput textInput;

    public void search(String text){
        textInput.sendKeys(text);
        button.click();
    }


}
