package com.noon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElectronicsPage extends BasePage{
    private final By samsungBrand = By.xpath("//img[contains(@alt,'samsung?sort')]");
    private final By sortBy = By.xpath("//button[contains(@class,'DesktopSortDropdown')]");
    private final By topRated = By.xpath("//a[text()='الأفضل تقييمًا']");
    private final By priceButton = By.xpath("//h3[text()='السعر']");
    private final By maxPrice = By.xpath("//input[@data-qa='filter-max-input']");
    private final By minPrice = By.xpath("//input[@data-qa='filter-min-input']");
    private final By priceSubmit = By.xpath("//button[text()='Go']");


    public ElectronicsPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnSamsung(){
        findElement(samsungBrand).click();
    }

    public void sortByTopRated(){
        findElement(sortBy).click();
        findElement(topRated).click();
    }

    public void clickOnPriceFilter(){
        findElement(priceButton).click();
    }

    public void setMaxPrice(){
        findElement(maxPrice).sendKeys("500");
    }

    public  void setMinPrice(){
        findElement(minPrice).sendKeys("100");
    }

    public void  clickOnPriceSubmit(){
        findElement(priceSubmit).click();
    }

}
