package com.johndoll.bluesourcebareselenium.pages;

import com.johndoll.bluesourceselenium.utility.Wait;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Jonathan Doll
 */
public class ProjectPage {
    
    private WebDriver driver;
    private long timer;
    private Wait wait = new Wait();
    
    public ProjectPage(WebDriver driver){
        this.driver = driver;
    }
    
    private WebElement btnAdd(){
        return driver.findElement(By.xpath("//button[contains(text(),'Add')]"));
    }
    
    private WebElement showInactives(){
        return driver.findElement(By.xpath(("//button[contains(text(), 'Show Inactives')]")));
    }
    
    private WebElement projectName(){
        return driver.findElement(By.id("project_name"));
    }
    
    private WebElement clientPartner(){
        return driver.findElement(By.id("project_client_partner_id"));
    }
    
    private WebElement btnAddTeamLead(){
        return driver.findElement(By.id("add-team-lead"));
    }
    
    private WebElement teamLeads(){
        return driver.findElement(By.id("project_leads"));
    }
    
    private WebElement status(){
        return driver.findElement(By.id("project_status"));
    }
    
    private WebElement startDate(){
        return driver.findElement(By.id("project_start_date"));
    }
    
    private WebElement endDate(){
        return driver.findElement(By.id("project_end_date"));
    }
    
    private WebElement btnCreateProject(){
        return driver.findElement(By.xpath("//input[@value='Create Project']"));
    }
    
    private WebElement btnClose(){
        return driver.findElement(By.xpath(("//button[contains(text(), 'Close')]")));
    }
    
    private boolean addFormExists(){
        return projectName().isDisplayed();
    }
    
    public boolean createSuccessful(){
        return driver.findElements(By.className("alert-success")).size() > 0;
    }
    
    public boolean createFailure(){
        return driver.findElements(By.className("alert-danger")).size() > 0;
    }
    
    private List<WebElement> allTeamLeads(){
        return driver.findElements(By.id("project_leads"));
    }
    
    private WebElement searchBar(){
        return driver.findElement(By.xpath("//input[@id='search-bar']"));
    }
    
    private boolean searchProjectExists(String project){
        return driver.findElements(By.linkText(project)).size() > 0;
    }
    
    private boolean nextPageExists(){
        return driver.findElements(By.linkText("»")).size() > 0;
    }
    
    private boolean previousPageExists(){
        return driver.findElements(By.linkText("«")).size() > 0;
    }
    
    private WebElement nextPage(){
        return driver.findElement(By.linkText("»"));
    }
    
    private WebElement previousPage(){
        return driver.findElement(By.linkText("«"));
    }
    
    private boolean projectNameColumnExists(){
        return driver.findElements(By.xpath("//a[contains(text(), 'Project Name')]")).size() > 0;
    }
    
    public void createProject(String projectName, String clientPartner, String teamLead, String status, String startDate, String endDate){
        btnAdd().click();

        timer = System.currentTimeMillis();
        while (!projectName().isDisplayed() && System.currentTimeMillis() - timer < 10000);
        
        wait.waitMilSec(500);
        projectName().sendKeys(projectName);
        clientPartner().sendKeys(clientPartner);
        String[] teamLeads = teamLead.split(", ");
        for(int i = 0; i < teamLeads.length; i++){
            btnAddTeamLead().click();
            allTeamLeads().get(i).sendKeys(teamLeads[i]);
        }
        status().sendKeys(status);
        startDate().sendKeys(startDate);
        endDate().sendKeys(endDate);
        btnCreateProject().click();
    }
    
    public boolean projectSearch(String projectName){
        boolean projectFound = false;
        searchBar().clear();
        searchBar().sendKeys(projectName);

        while (!projectFound) {
            timer = System.currentTimeMillis();
            while(!projectNameColumnExists() && System.currentTimeMillis() - timer < 10000)
            wait.waitMilSec(500);
            projectFound = searchProjectExists(projectName);
            if(nextPageExists() && nextPage().isEnabled()) {
                nextPage().click();
            } else {
                break;
            }
        }
        
        return projectFound;
    }
}
