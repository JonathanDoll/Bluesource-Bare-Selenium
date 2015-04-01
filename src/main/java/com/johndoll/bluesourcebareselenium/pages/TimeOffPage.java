package com.johndoll.bluesourcebareselenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Jonathan Doll
 */
public class TimeOffPage {
    
    private WebDriver driver;
    
    public TimeOffPage(WebDriver driver){
        this.driver = driver;
    }
    
    public WebElement btnRequestTimeOff(){
        return driver.findElement(By.xpath("//button[contains(text(), 'Request time off')]"));
    }
    
    public Boolean timeOffMessage(){
        return driver.findElements(By.xpath("//h1[contains(text(), 'Time Off Details')]")).size() > 0;
    }
    
    public WebElement startDate(){
        return driver.findElement(By.id("vacation_start_date"));
    }
    
    public WebElement endDate(){
        return driver.findElement(By.id("vacation_end_date"));
    }
    
    public WebElement vacationType(){
        return driver.findElement(By.id("vacation_vacation_type"));
    }
    
    public WebElement halfDay(){
        return driver.findElement(By.id("vacation_half_day"));
    }
    
    public WebElement cc(){
        return driver.findElement(By.id("cc_check_box"));
    }
    
    public WebElement memo(){
        return driver.findElement(By.id("memo"));
    }
    
    public boolean createSuccessful(){
        return driver.findElements(By.className("alert-success")).size() > 0;
    }
    
    public boolean createFailure(){
        return driver.findElements(By.className("alert-danger")).size() > 0;
    }
    
    public WebElement btnCreateTimeOffRequest(){
        return driver.findElement(By.xpath("//input[@value='Request time off']"));
    }
}
