package com.johndoll.bluesourcebareselenium.tests;

import com.johndoll.bluesourcebareselenium.pages.Links;
import com.johndoll.bluesourcebareselenium.pages.LoginPage;
import com.johndoll.bluesourcebareselenium.pages.TitlePage;
import com.johndoll.bluesourceselenium.utility.ExcelReader;
import com.johndoll.bluesourceselenium.utility.ResourceLocation;
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
public class Titles {
    
    private static WebDriver driver;
    private static Links link;
    private static TitlePage title;
    private long timer;
            
    @BeforeClass
    public static void setUpClass() throws Exception {
        driver = new FirefoxDriver();
        driver.get("http://bluesourcestaging.herokuapp.com/");
        LoginPage login = new LoginPage(driver);
        login.login("company.admin", "The McRib is back");
        link = new Links(driver);
        title = new TitlePage(driver);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        link.logout().click();
        driver.close();
    }
    
    @DataProvider
    public Object[][] addTitleData(){
        return new ExcelReader(ResourceLocation.TestDataLocation + "AddTitle.xlsx").worksheetToArray(1);
    }
    
    @Test(dataProvider = "addTitleData", groups = "add", threadPoolSize = 3)
    public void addTitle(String titleName){
        link.admin().click();
        link.titles().click();
        
        title.addTitle(titleName);
        
        timer = System.currentTimeMillis();
        while (!title.createFailure() && !title.createSuccessful() && System.currentTimeMillis() - timer < ResourceLocation.PageWaitTime);
        
        try{
            assertTrue(title.createSuccessful(), "The title was successfully created");
            assertTrue(title.titleExists(titleName), "The title was succesfully found in the list");
        }catch(AssertionError e){
            System.err.println(e);
            assertTrue(false);
        }
    }
    
    @DataProvider
    public Object[][] editTitleData(){
        System.out.println("edit title data");
        return new ExcelReader(ResourceLocation.TestDataLocation + "EditTitle.xlsx").worksheetToArray(1);
    }
    
    @Test(dataProvider = "editTitleData", groups = "edit", dependsOnGroups = "add", threadPoolSize = 3)
    public void editTitle(String titleName, String newTitleName){
        link.admin().click();
        link.titles().click();
        
        title.editTitle(titleName, newTitleName);
        
        timer = System.currentTimeMillis();
        while (!title.createFailure() && !title.createSuccessful() && System.currentTimeMillis() - timer < ResourceLocation.PageWaitTime);
        
        try{
            assertTrue(title.createSuccessful(), "The title was successfully edited");
            assertTrue(title.titleExists(newTitleName), "The title was succesfully found in the list");
        }catch(AssertionError e){
            System.err.println(e);
            assertTrue(false);
        }
    }
    
    @DataProvider
    public Object[][] deleteTitleData(){
        System.out.println("delete title data");
        return new ExcelReader(ResourceLocation.TestDataLocation + "DeleteTitle.xlsx").worksheetToArray(1);
    }
    
    @Test(dataProvider = "deleteTitleData", dependsOnGroups = "edit", threadPoolSize = 3)
    public void deleteTitle(String titleName){
        link.admin().click();
        link.titles().click();
        
        title.deleteTitle(titleName);
        
        timer = System.currentTimeMillis();
        while (!title.createFailure() && !title.createSuccessful() && System.currentTimeMillis() - timer < ResourceLocation.PageWaitTime);
        
        try{
            assertTrue(title.createSuccessful(), "The title was successfully deleted");
            assertTrue(!title.titleExists(titleName), "The title was succesfully removed from the list");
        }catch(AssertionError e){
            System.err.println(e);
            assertTrue(false);
        }
    }
    
}
