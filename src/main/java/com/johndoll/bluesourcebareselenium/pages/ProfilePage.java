package com.johndoll.bluesourcebareselenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Jonathan Doll
 */
public class ProfilePage {
    
    private WebDriver driver;
    
    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }
    
    public WebElement view(){
        return driver.findElement(By.linkText("View"));
    }
    
}
