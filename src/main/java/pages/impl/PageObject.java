package pages.impl;

import annotations.Entity;
import annotations.Inject;
import elements.Button;
import elements.TextInput;
import map.ModulesDependencies;
import pages.Page;

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
