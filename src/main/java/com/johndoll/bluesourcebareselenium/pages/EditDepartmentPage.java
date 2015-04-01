package com.johndoll.bluesourcebareselenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Jonathan Doll
 */
public class EditDepartmentPage {

    private WebDriver driver;

    public EditDepartmentPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement departmentName() {
        return driver.findElement(By.xpath("//input[@id='department_name']"));
    }

    public WebElement parentDepartmentName() {
        return driver.findElement(By.xpath("//select[@id='department_department_id']"));
    }

    public WebElement btnUpdateDepartment() {
        return driver.findElement(By.xpath("//input[@value='Update Department']"));
    }

}
