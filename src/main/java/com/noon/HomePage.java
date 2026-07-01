package com.noon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends  BasePage{
    private final By electronicsCategory = By.xpath("//span[text()='Electronics']");
    private static final Logger logger = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ElectronicsPage clickOnElectronicsCategory(){
        logger.info("Clicking on electronics category");
        findElement(electronicsCategory).click();
        try {
            Actions actions = new Actions(driver);
            actions.moveByOffset(0, 0).build().perform();
            logger.info("Moved mouse to offset (0,0) to clear hover menu");
        } catch (Exception e) {
            logger.warn("Failed to move mouse offset, trying alternative blur", e);
        }
        return  new ElectronicsPage(driver);
    }
}