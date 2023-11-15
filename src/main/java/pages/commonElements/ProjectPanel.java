package pages.commonElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectPanel {
    private final WebDriver driver;
    private static final By projectLink = org.openqa.selenium.By.xpath("//a[@class='jenkins-table__link model-link inside']");
    private static final By dropDownProjectButton = By.xpath("//button[@class='jenkins-menu-dropdown-chevron' and contains(@data-href, 'job')]");
    private static final By deleteProjectButton = By.xpath("//button[contains(@href,'doDelete')]");
    private static final By renameProjectButton = By.partialLinkText("Rename");
    private static final By configureProjectButton = By.partialLinkText("Configure");
    private static final By changesButton = By.partialLinkText("Changes");
    private static final By workspaceButton = By.partialLinkText("Workspace");
    private static final By buildProjectButton = By.xpath("//button[contains(normalize-space(), 'Build')]");


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


}
