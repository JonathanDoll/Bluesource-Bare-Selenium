package com.johndoll.bluesourceselenium.tests;

import com.johndoll.bluesourcebareselenium.pages.EmployeePage;
import com.johndoll.bluesourcebareselenium.pages.Links;
import com.johndoll.bluesourcebareselenium.pages.LoginPage;
import com.johndoll.bluesourcebareselenium.pages.TimeOffPage;
import com.johndoll.bluesourceselenium.utility.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Jonathan Doll
 */
public class Login {
    private static WebDriver driver;
    
    public Login(){
        
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        driver = new FirefoxDriver();
        driver.get("http://bluesourcestaging.herokuapp.com/");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        driver.close();
    }
    
    @DataProvider
    public Object[][] loginData(){
        ExcelReader loginData = new ExcelReader("src\\test\\resources\\LoginData.xlsx");
        return loginData.worksheetToArray(1);
    }
    
    @Test(dataProvider = "loginData", threadPoolSize = 3)
    public void login(String username, String password){
        LoginPage login = new LoginPage(driver);
        login.login("company.admin", "ham");
        
        EmployeePage employee = new EmployeePage(driver);
        TimeOffPage timeOff = new TimeOffPage(driver);
        if(employee.welcomeMessage()){
            assertTrue(employee.welcomeMessage(), "Welcome Message Displayed.");
        }else if(timeOff.timeOffMessage()){
            assertTrue(timeOff.timeOffMessage(), "Time Off Message Displayed");
        } 
        
        Links link = new Links(driver);
        link.logout().click();
        
        assertTrue(login.usernameExists(), "Logout was successful");
    }
    
}
