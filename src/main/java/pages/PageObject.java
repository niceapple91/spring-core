package pages;

import driver.WebDriverInstance;
import elements.Button;
import elements.TextInput;
import elements.impl.ButtonImpl;
import elements.impl.TextInputImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PageObject {

    private Button button;
    private TextInput textInput;

    public void search(String text){
        button = new ButtonImpl();
        textInput = new TextInputImpl();
        textInput.sendKeys(text);
        button.click();
    }


}
