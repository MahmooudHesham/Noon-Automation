package com.noon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends  BasePage{
    private final By electronicsCategory = By.xpath("//span[text()='الإلكترونيات']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ElectronicsPage clickOnElectronicsCategory(){
        findElement(electronicsCategory).click();
        return  new ElectronicsPage(driver);
    }
}
