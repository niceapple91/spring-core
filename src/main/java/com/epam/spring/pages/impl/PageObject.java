package com.epam.spring.pages.impl;

import com.epam.spring.annotations.*;
import com.epam.spring.elements.Button;
import com.epam.spring.elements.TextInput;
import com.epam.spring.pages.Page;

@Entity
@Profiling
public class PageObject implements Page{

    @Randome(min = 2, max = 7)
    private int repeat;
    @Inject
    private Button button;
    @Inject
    private TextInput textInput;

    public void search(String text){
        for (int i = 0; i < repeat; i++){
            System.out.println("Repeat " + i);
        }
        textInput.sendKeys(text);
        button.click();
    }

    @Init
    private void init(){
        System.out.println("In init in PageObject");
    }

    @PostInit
    private void postInit(){
        System.out.println("In postInit in PageObject");
    }


}
