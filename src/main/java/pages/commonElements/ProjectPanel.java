package pages.commonElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pages.HomePage;
import support.JenkinsUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectPanel {
    private final WebDriver driver;
    private final By projectLink = By.xpath("//a[@class='jenkins-table__link model-link inside']");
    private final By dropDownProjectButton = By.xpath("//button[@class='jenkins-menu-dropdown-chevron' and contains(@data-href, 'job')]");
    private final By deleteProjectButton = By.xpath("//button[contains(@href,'doDelete')]");
    private final By renameProjectButton = By.partialLinkText("Rename");
    private final By configureProjectButton = By.partialLinkText("Configure");
    private final By changesButton = By.partialLinkText("Changes");
    private final By workspaceButton = By.partialLinkText("Workspace");
    private final By buildProjectButton = By.xpath("//button[contains(normalize-space(), 'Build')]");

    public ProjectPanel(WebDriver driver) {
        this.driver = driver;
    }

    private void clickDeleteButton() {
        driver.findElement(deleteProjectButton).click();
    }

    private void clickRenameButton() {
        driver.findElement(renameProjectButton).click();
    }

    public void clickDropDownProjectButton(String projectName) {
        new Actions(driver)
                .moveToElement(driver.findElement(By.linkText(projectName)))
                .moveToElement(driver.findElement(dropDownProjectButton))
                .click()
                .perform();
    }

    public void deleteProjectByName(String projectName) {
        clickDropDownProjectButton(projectName);
        clickDeleteButton();
        driver.switchTo().alert().accept();
    }

    public List<String> getAllProjectsNames() {
        return driver
                .findElements(projectLink)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void deleteAllProjects() {
        for (String projectName : getAllProjectsNames()) {
            deleteProjectByName(projectName);
        }
    }

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
        HomePage page = new HomePage(driver);
        JenkinsUtils.logIn(driver);

        page.getProjectPanel().clickDropDownProjectButton("dfs");
        page.getProjectPanel().clickRenameButton();
    }


}
