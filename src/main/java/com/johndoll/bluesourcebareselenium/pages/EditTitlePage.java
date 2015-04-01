package com.johndoll.bluesourcebareselenium.pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author jonathan.doll
 */
public class EditTitlePage {
    private WebDriver driver;
    
    public EditTitlePage(WebDriver driver){
        this.driver = driver;
    }
    
    public WebElement titleName(){
        return driver.findElement(By.id("title_name"));
    }
    
    public WebElement btnUpdateTitle(){
        return driver.findElement(By.xpath("//input[@value='Update Title']"));
    }
}
