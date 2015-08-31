package com.johndoll.bluesourcebareselenium.pages;

import com.johndoll.bluesourcebareselenium.utility.Wait;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Jonathan Doll
 */
public class EmployeePage {

    private WebDriver driver;
    private Wait wait = new Wait();
    private long timer;

    public EmployeePage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement btnAdd() {
        return driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
    }

    private WebElement username() {
        return driver.findElement(By.id("employee_username"));
    }

    private WebElement firstName() {
        return driver.findElement(By.id("employee_first_name"));
    }

    private WebElement lastName() {
        return driver.findElement(By.id("employee_last_name"));
    }

    private WebElement btnCreateEmployee() {
        return driver.findElement(By.name("commit"));
    }

    public boolean createSuccessful() {
        return driver.findElements(By.className("alert-success")).size() > 0;
    }

    public boolean createFailure() {
        return driver.findElements(By.className("alert-danger")).size() > 0;
    }

    public Boolean welcomeMessage() {
        return driver.findElements(By.xpath("//div/h1[contains(text(),'Welcome')]")).size() > 0;
    }

    public WebElement nameLink() {
        return driver.findElement(By.xpath("//div//h1[contains(text(),'Welcome')]//a"));
    }

    private WebElement searchBar() {
        return driver.findElement(By.xpath("//input[@id='search-bar']"));
    }

    private WebElement searchFirstName(String firstName) {
        return driver.findElement(By.linkText(firstName));
    }

    private WebElement searchLastName(String lastName) {
        return driver.findElement(By.linkText(lastName));
    }

    private boolean searchFirstNameExists(String firstName) {
        return driver.findElements(By.linkText(firstName)).size() > 0;
    }

    private boolean searchLastNameExists(String lastName) {
        return driver.findElements(By.linkText(lastName)).size() > 0;
    }

    private WebElement title() {
        return driver.findElement(By.id("employee_title_id"));
    }

    private WebElement role() {
        return driver.findElement(By.id("employee_role"));
    }

    private WebElement manager() {
        return driver.findElement(By.id("employee_manager_id"));
    }

    private WebElement status() {
        return driver.findElement(By.id("employee_status"));
    }

    private WebElement bridgeTime() {
        return driver.findElement(By.id("employee_bridge_time"));
    }

    private WebElement location() {
        return driver.findElement(By.id("employee_location"));
    }

    private WebElement startDate() {
        return driver.findElement(By.id("employee_start_date"));
    }

    private WebElement cellPhone() {
        return driver.findElement(By.id("employee_cell_phone"));
    }

    private WebElement officePhone() {
        return driver.findElement(By.id("employee_office_phone"));
    }

    private WebElement email() {
        return driver.findElement(By.id("employee_email"));
    }

    private WebElement imName() {
        return driver.findElement(By.id("employee_im_name"));
    }

    private WebElement imClient() {
        return driver.findElement(By.id("employee_im_client"));
    }

    private WebElement department() {
        return driver.findElement(By.id("employee_department_id"));
    }

    private boolean firstNameTableExists() {
        return driver.findElements(By.linkText("First Name")).size() > 0;
    }

    private boolean nextPageExists() {
        return driver.findElements(By.linkText("»")).size() > 0;
    }

    private boolean previousPageExists() {
        return driver.findElements(By.linkText("«")).size() > 0;
    }

    private WebElement nextPage() {
        return driver.findElement(By.linkText("»"));
    }

    private WebElement previousPage() {
        return driver.findElement(By.linkText("«"));
    }

    public void createNewEmployee(String username, String firstName, String lastName, String title, String role, String manager, String status, String bridgeTime, String location, String startDate, String cellPhone, String officePhone, String email, String imName, String imClient, String department) {
        btnAdd().click();

        timer = System.currentTimeMillis();
        while (!firstName().isDisplayed() && System.currentTimeMillis() - timer < 10000);
        wait.waitMilSec(500);

        username().sendKeys(username);
        firstName().sendKeys(firstName);
        lastName().sendKeys(lastName);
        title().sendKeys(title);
        role().sendKeys(role);
        manager().sendKeys(manager);
        status().sendKeys(status);
        bridgeTime().sendKeys(bridgeTime);
        location().sendKeys(location);
        startDate().sendKeys(startDate);
        cellPhone().sendKeys(cellPhone);
        officePhone().sendKeys(officePhone);
        email().sendKeys(email);
        imName().sendKeys(imName);
        imClient().sendKeys(imClient);
        department().sendKeys(department);
        wait.waitMilSec(500);
        btnCreateEmployee().click();
    }

    public boolean employeeSearch(String firstName, String lastName) {
        boolean employeeFound = false;
        searchBar().clear();
        searchBar().sendKeys(firstName + " " + lastName);

        while (!employeeFound) {
            timer = System.currentTimeMillis();
            while (!firstNameTableExists() && System.currentTimeMillis() - timer < 15000);
            wait.waitMilSec(500);

            List<WebElement> employeeFirstNames = driver.findElements(By.linkText(firstName));
            List<WebElement> employeeLastNames = driver.findElements(By.linkText(lastName));

            for (WebElement employeeFirstName : employeeFirstNames) {
                for (WebElement employeeLastName : employeeLastNames) {
                    if (employeeFirstName.getLocation().getY() == employeeLastName.getLocation().getY()) {
                        employeeFound = true;
                        break;
                    }
                }
            }
            if (nextPageExists() && nextPage().isEnabled()) {
                nextPage().click();
            } else {
                break;
            }
        }
        return employeeFound;
    }

}
