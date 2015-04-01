package com.johndoll.bluesourcebareselenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Jonathan Doll
 */
public class NewTitlePage {
    private WebDriver driver;
    
    public NewTitlePage(WebDriver driver){
        this.driver = driver;
    }
    
    public WebElement titleName(){
        return driver.findElement(By.id("title_name"));
    }
    
    public WebElement btnCreateTitle(){
        return driver.findElement(By.xpath("//input[@value='Create Title']"));
    }
}
