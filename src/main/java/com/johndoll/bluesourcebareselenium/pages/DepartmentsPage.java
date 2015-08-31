package com.johndoll.bluesourcebareselenium.pages;

import com.johndoll.bluesourcebareselenium.utility.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Jonathan Doll
 */
public class DepartmentsPage {
    private WebDriver driver;
    private Wait wait = new Wait();
    
    public DepartmentsPage(WebDriver driver){
        this.driver = driver;
    }
    
    private WebElement editDepartment(String departmentName){
        return driver.findElement(By.xpath("//li[contains(text(), '" + departmentName + "')]/div/a/span[@class='glyphicon glyphicon-pencil']"));
    }
    
    private WebElement deleteDepartment(String departmentName){
        return driver.findElement(By.xpath("//li[contains(text(), '" + departmentName + "')]/div/a/span[@class='glyphicon glyphicon-trash']"));
    }
    
    private WebElement addDepartment(){
        return driver.findElement(By.linkText("Add Department"));
    }
    
    private WebElement addSubdepartment(String departmentName){
        return driver.findElement(By.xpath("//li[contains(text(), '" + departmentName + "')]/a[contains(text(), 'Add Subdepartment')]"));
    }
    
    public boolean departmentExists(String departmentName){
        return driver.findElements(By.xpath("//li[contains(text(), '" + departmentName + "')]")).size() > 0;
    }
    
    public boolean createSuccessful(){
        return driver.findElements(By.className("alert-success")).size() > 0;
    }
    
    public boolean createFailure(){
        return driver.findElements(By.className("alert-danger")).size() > 0;
    }
    
    public void addDept(String departmentName, String parentDepartmentName){
        addDepartment().click();
        
        wait.waitMilSec(250);
        AddDepartmentPage add = new AddDepartmentPage(driver);
        add.departmentName().sendKeys(departmentName);
        add.parentDepartmentName().sendKeys(parentDepartmentName);
        add.btnCreateDepartment().click();
    }
    
    public void addSubdept(String departmentName, String subdepartmentName, String parentDepartmentName){
        addSubdepartment(departmentName).click();
        
        wait.waitMilSec(250);
        AddSubdepartmentPage add = new AddSubdepartmentPage(driver);
        add.departmentName().sendKeys(subdepartmentName);
        add.parentDepartmentName().sendKeys(parentDepartmentName);
        add.btnCreateDepartment().click();
    }
    
    public void editDept(String departmentName, String newDepartmentName, String parentDepartment){
        editDepartment(departmentName).click();
        
        wait.waitMilSec(250);
        EditDepartmentPage edit = new EditDepartmentPage(driver);
        edit.departmentName().clear();
        edit.departmentName().sendKeys(newDepartmentName);
        edit.parentDepartmentName().sendKeys(parentDepartment);
        edit.btnUpdateDepartment().click();
    }
    
    public void deleteDept(String departmentName){
        deleteDepartment(departmentName).click();
        driver.switchTo().alert().accept();
    }
    
}
