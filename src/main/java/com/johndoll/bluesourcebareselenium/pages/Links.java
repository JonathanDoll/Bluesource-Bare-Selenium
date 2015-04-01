package com.johndoll.bluesourcebareselenium.pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Jonathan Doll
 */
public class Links {

    private WebDriver driver;

    public Links(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement logout() {
        return driver.findElement(By.linkText("Logout"));
    }

    public WebElement employees() {
        return driver.findElement(By.linkText("Employees"));
    }

    public WebElement projects() {
        return driver.findElement(By.linkText("Projects"));
    }

    public WebElement directory() {
        return driver.findElement(By.linkText("Directory"));
    }

    public WebElement calendar() {
        return driver.findElement(By.linkText("Calendar"));
    }

    public WebElement titles() {
        return driver.findElement(By.linkText("Titles"));
    }

    public WebElement admin() {
        return driver.findElement(By.linkText("Admin"));
    }
    
    public WebElement home(){
        return driver.findElement(By.className("navbar-brand"));
    }
    
    public WebElement departments(){
        return driver.findElement(By.linkText("Departments"));
    }

}
