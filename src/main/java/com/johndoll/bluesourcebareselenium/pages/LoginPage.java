package com.johndoll.bluesourcebareselenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Jonathan Doll
 */
public class LoginPage {
    private WebDriver driver;
    
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
           
    public boolean usernameExists(){
        return driver.findElements(By.id("employee_username")).size() > 0;
    }
    
    public WebElement username(){
        return driver.findElement(By.id("employee_username"));
    }
    
    public WebElement password(){
        return driver.findElement(By.id("employee_password"));
    }
    
    public WebElement btnLogin(){
        return driver.findElement(By.xpath("//input[@value='Login']"));
    }
    
    public void login(String username, String password){
        username().clear();
        username().sendKeys(username);
        password().clear();
        password().sendKeys(password);
        btnLogin().click();
        
    }
}
