package com.johndoll.bluesourcebareselenium.tests;

import com.johndoll.bluesourcebareselenium.pages.DepartmentsPage;
import com.johndoll.bluesourcebareselenium.pages.Links;
import com.johndoll.bluesourcebareselenium.pages.LoginPage;
import com.johndoll.bluesourcebareselenium.utility.ExcelReader;
import com.johndoll.bluesourcebareselenium.utility.ResourceLocation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import org.testng.annotations.*;

/**
 * @author Jonathan Doll
 */
public class Departments {
    
    private static WebDriver driver;
    private static Links link;
    private static DepartmentsPage dept;
    private long timer;
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        driver = new FirefoxDriver();
        driver.get("http://bluesourcestaging.herokuapp.com/");
        LoginPage login = new LoginPage(driver);
        login.login("company.admin", "The McRib is back");
        link = new Links(driver);
        dept = new DepartmentsPage(driver);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        link.logout().click();
        driver.close();
    }
    
    @DataProvider
    public Object[][] addDeptData(){
        return new ExcelReader(ResourceLocation.TestDataLocation + "AddDept.xlsx").worksheetToArray(1);
    }
    
    @Test(dataProvider = "addDeptData", groups = "add", threadPoolSize = 3)
    public void addDept(String deptName, String parentDeptName){
        link.admin().click();
        link.departments().click();
        
        dept.addDept(deptName, parentDeptName);
        
        timer = System.currentTimeMillis();
        while(!dept.createSuccessful() && !dept.createFailure() && System.currentTimeMillis() - timer < ResourceLocation.PageWaitTime);
        
        try{
            assertTrue(dept.createSuccessful(), "The department was successfully created");
        }catch(AssertionError e){
            System.err.println(e);
            assertTrue(dept.departmentExists(deptName), "The department was successfully found in the list");
            System.out.println("The department was found in the list");
            fail();
        }
    }
    
    @DataProvider
    public Object[][] addSubdeptData(){
        return new ExcelReader(ResourceLocation.TestDataLocation + "AddSubdept.xlsx").worksheetToArray(1);
    }
    
    @Test(dataProvider = "addSubdeptData", groups = "addsub", dependsOnGroups = "add", threadPoolSize = 3)
    public void addSubdept(String deptName, String subdeptName, String parentDeptName){
        link.admin().click();
        link.departments().click();
        
        dept.addSubdept(deptName, subdeptName, parentDeptName);
        
        timer = System.currentTimeMillis();
        while(!dept.createSuccessful() && !dept.createFailure() && System.currentTimeMillis() - timer < ResourceLocation.PageWaitTime);
        
        try{
            assertTrue(dept.createSuccessful(), "The subdepartment was successfully created");
        }catch(AssertionError e){
            System.err.println(e);
            assertTrue(dept.departmentExists(subdeptName), "The subdepartment was successfully found in the list");
            System.out.println("The subdepartment was found in the list");
            fail();
        }
    }
    
    @DataProvider
    public Object[][] editDeptData(){
        return new ExcelReader(ResourceLocation.TestDataLocation + "EditDept.xlsx").worksheetToArray(1);
    }
    
    @Test(dataProvider = "editDeptData", groups = "edit", dependsOnGroups = "addsub", threadPoolSize = 3)
    public void editDept(String deptName, String newDeptName, String parentDeptName){
        link.admin().click();
        link.departments().click();
        
        dept.editDept(deptName, newDeptName, parentDeptName);
        
        timer = System.currentTimeMillis();
        while(!dept.createSuccessful() && !dept.createFailure() && System.currentTimeMillis() - timer < ResourceLocation.PageWaitTime);
        
        try{
            assertTrue(dept.createSuccessful(), "The department was successfully edited");
        }catch(AssertionError e){
            System.err.println(e);
            assertTrue(dept.departmentExists(newDeptName), "The department was successfully found in the list");
            System.out.println("The department was found in the list");
            fail();
        }
    }
    
    @DataProvider
    public Object[][] deleteDeptData(){
        return new ExcelReader(ResourceLocation.TestDataLocation + "DeleteDept.xlsx").worksheetToArray(1);
    }
    
    @Test(dataProvider = "deleteDeptData", dependsOnGroups = "edit", threadPoolSize = 3)
    public void deleteDept(String deptName){
        link.admin().click();
        link.departments().click();
        
        dept.deleteDept(deptName);
        
        timer = System.currentTimeMillis();
        while(!dept.createSuccessful() && !dept.createFailure() && System.currentTimeMillis() - timer < ResourceLocation.PageWaitTime);
        
        try{
            assertTrue(dept.createSuccessful(), "The department was successfully deleted");
        }catch(AssertionError e){
            System.err.println(e);
            assertTrue(!dept.departmentExists(deptName), "The department was successfully removed from the list");
            System.out.println("The department was succesfully deleted.");
            fail();
        }
    }
}
