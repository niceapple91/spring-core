package pages;

import driver.WebDriverInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PageObject {

    private WebElement button;
    private WebElement textInput;

    public void search(String text){
        WebDriverInstance.getDriverInstance();
        WebDriverInstance.loadPage();
        button = WebDriverInstance.getDriverInstance().findElement(By.name("btnG"));
        textInput = WebDriverInstance.getDriverInstance().findElement(By.id("lst-ib"));
        textInput.sendKeys(text);
        button.click();
    }


}
