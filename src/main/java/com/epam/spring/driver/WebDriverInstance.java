package com.epam.spring.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverInstance {

    static private String url = "https://www.google.com.ua";

    private static WebDriver driver;

    public static synchronized WebDriver getDriverInstance() {
        if (driver != null) {
            return driver;
        }
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        return driver;
    }

    public static void stopDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static synchronized void loadPage() {
        getDriverInstance().get(url);
    }

}
