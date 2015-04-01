package com.johndoll.bluesourcebareselenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Jonathan Doll
 */
public class TitlePage {

    private WebDriver driver;

    public TitlePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement deleteATitle(String title) {
        return driver.findElement(By.xpath("//td[contains(text(), '" + title + "')]//div//a//span[@class='glyphicon glyphicon-trash']"));
    }

    public WebElement editATitle(String title) {
        return driver.findElement(By.xpath("//td[contains(text(), '" + title + "')]//div//a//span[@class='glyphicon glyphicon-pencil']"));
    }

    public WebElement addATitle() {
        return driver.findElement(By.linkText("New Title"));
    }
    
    public boolean createSuccessful(){
        return driver.findElements(By.className("alert-success")).size() > 0;
    }
    
    public boolean createFailure(){
        return driver.findElements(By.className("alert-danger")).size() > 0;
    }
    
    public boolean titleExists(String title){
        return driver.findElements(By.xpath("//td[contains(text(), '" + title + "')]//div//a//span[@class='glyphicon glyphicon-pencil']")).size() > 0;
    }
    
    public void addTitle(String titleName){
        addATitle().click();
        NewTitlePage ntp = new NewTitlePage(driver);
        
        ntp.titleName().sendKeys(titleName);
        ntp.btnCreateTitle().click();
    }
    
    public void editTitle(String titleName, String newTitleName){
        editATitle(titleName).click();
        EditTitlePage etp = new EditTitlePage(driver);
        
        etp.titleName().clear();
        etp.titleName().sendKeys(newTitleName);
        etp.btnUpdateTitle().click();
    }
    
    public void deleteTitle(String titleName){
        deleteATitle(titleName).click();
        driver.switchTo().alert().accept();
    }
    
}
