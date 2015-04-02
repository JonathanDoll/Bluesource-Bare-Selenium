package com.johndoll.bluesourcebareselenium.tests;

import com.johndoll.bluesourcebareselenium.pages.EmployeePage;
import com.johndoll.bluesourcebareselenium.pages.Links;
import com.johndoll.bluesourcebareselenium.pages.LoginPage;
import com.johndoll.bluesourceselenium.utility.ExcelReader;
import com.johndoll.bluesourceselenium.utility.ResourceLocation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Jonathan Doll
 */
public class AddEmployee {

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
    public Object[][] addEmployeeData() {
        return new ExcelReader(ResourceLocation.TestDataLocation + "AddEmployee.xlsx").worksheetToArray(1);
    }

    @Test(dataProvider = "addEmployeeData", threadPoolSize = 3)
    public void addEmployee(String username, String firstName, String lastName, String title, String role, String manager, String status, String bridgeTime, String location, String startDate, String cellPhone, String officePhone, String email, String imName, String imClient, String department) {
        Links link = new Links(driver);
        link.employees().click();

        EmployeePage employee = new EmployeePage(driver);
        employee.createNewEmployee(username, firstName, lastName, title, role, manager, status, bridgeTime, location, startDate, cellPhone, officePhone, email, imName, imClient, department);

        long timer = System.currentTimeMillis();
        while (!employee.createFailure() && !employee.createSuccessful() && System.currentTimeMillis() - timer < ResourceLocation.PageWaitTime);
        
        try{
        assertTrue(employee.createSuccessful(), "Employee Created Successfully");
        }catch(AssertionError e){
            System.err.println(e);
            assertTrue(employee.employeeSearch(firstName, lastName), "Employee successfully found in list");
            System.out.println("Employee found in list");
            fail();
        }
    }

}
