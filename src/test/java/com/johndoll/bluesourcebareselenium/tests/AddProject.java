package com.johndoll.bluesourcebareselenium.tests;

import com.johndoll.bluesourcebareselenium.pages.Links;
import com.johndoll.bluesourcebareselenium.pages.LoginPage;
import com.johndoll.bluesourcebareselenium.pages.ProjectPage;
import com.johndoll.bluesourcebareselenium.utility.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.johndoll.bluesourcebareselenium.utility.ResourceLocation;
import static org.testng.Assert.fail;

/**
 * @author Jonathan Doll
 */
public class AddProject {
    
    private static WebDriver driver;
    private static Links link;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        driver = new FirefoxDriver();
        driver.get("http://bluesourcestaging.herokuapp.com/");
        LoginPage login = new LoginPage(driver);
        login.login("company.admin", "The McRib is back");
        link = new Links(driver);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        link.logout().click();
        driver.close();
    }
    
    @DataProvider
    public Object[][] addProjectData(){
        return new ExcelReader(ResourceLocation.TestDataLocation + "AddProject.xlsx").worksheetToArray(1);
    }
    
    @Test(dataProvider = "addProjectData",  threadPoolSize = 3)
    public void addProject(String projectName, String clientPartner, String teamLead, String status, String startDate, String endDate){
        Links links = new Links(driver);
        links.projects().click();

        ProjectPage project = new ProjectPage(driver);
        project.createProject(projectName, clientPartner, teamLead, status, startDate, endDate);
        
        long timer = System.currentTimeMillis();
        while (!project.createFailure() && !project.createSuccessful() && System.currentTimeMillis() - timer < ResourceLocation.PageWaitTime);
        
        try{
            assertTrue(project.createSuccessful(), "Project was created successfully");
            
        }catch(AssertionError e){
            System.err.println(e);
            assertTrue(project.projectSearch(projectName), "Project was found in the list");
            System.out.println("Project found in list");
            fail();
        }
    }
    
}
