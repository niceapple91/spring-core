package com.epam.spring.pages.impl;

import com.epam.spring.annotations.Entity;
import com.epam.spring.annotations.Inject;
import com.epam.spring.annotations.Randome;
import com.epam.spring.elements.Button;
import com.epam.spring.elements.TextInput;
import com.epam.spring.pages.Page;

@Entity
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


}
