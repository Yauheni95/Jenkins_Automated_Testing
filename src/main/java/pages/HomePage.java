package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasicPage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final By dropDownProjectButton = By.xpath("//button[@class='jenkins-menu-dropdown-chevron' and contains(@data-href, 'job')]");

    private final By deleteProjectButton = By.xpath("//button[contains(@href,'doDelete')]");

    private final By projectLink = By.xpath("//a[@class='jenkins-table__link model-link inside']");

    private final By DescriptionButton = By.id("description-link");

    private final By textDescriptionArea = By.name("description");

    private final By descriptionArea = By.cssSelector("#description > div:nth-child(1)");

    private final By saveDescriptionButton = By.name("Submit");

    private final By welcomeJenkinsTextMessage = By.xpath("//h1[text() = 'Welcome to Jenkins!']");


    public BuildHistoryPage goToBuildHistoryPage() {
        clickLink("Build History");
        return new BuildHistoryPage(driver);
    }

    public NewItemPage goToNewItemPage() {
        clickLink("New Item");
        return new NewItemPage(driver);
    }

    public PeoplePage goToPeoplePage() {
        clickLink("People");
        return new PeoplePage(driver);
    }

    public ManageJenkinsPage goToManageJenkinsPage() {
        clickLink("Manage Jenkins");
        return new ManageJenkinsPage(driver);
    }

    public MyViewsPage goToMyViewsPage() {
        clickLink("My Views");
        return new MyViewsPage(driver);
    }

    public JenkinsApiPage goToApiPage() {
        clickLink("REST API");
        return new JenkinsApiPage(driver);
    }

    public void clickDropDownProjectButton(String projectName) {
        new Actions(driver)
                .moveToElement(driver.findElement(By.linkText(projectName)))
                .moveToElement(driver.findElement(dropDownProjectButton))
                .click()
                .perform();
    }

    private void clickDeleteButtonInDropDownMenu() {
        driver.findElement(deleteProjectButton).click();
    }

    public void deleteJobByName(String projectName) {
        clickDropDownProjectButton(projectName);
        clickDeleteButtonInDropDownMenu();
        driver.switchTo().alert().accept();
    }

    public String getDescriptionButtonText() {
        return driver.findElement(DescriptionButton).getText();
    }

    public List<String> getAllProjectsNames() {
        return driver
                .findElements(projectLink)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isProjectExist(String projectName) {
        return !driver.findElements(By.id("job_" + projectName)).isEmpty();
    }

    public boolean isWelcomeMessageDisplayed() {
        return !driver.findElements(welcomeJenkinsTextMessage).isEmpty();
    }

    public void addDescription(String text) {
        clickAddDescriptionButton();
        sendTextToDescriptionArea(text);
        clickSaveDescriptionButton();
    }

    public void clearDescriptionArea() {
        clickAddDescriptionButton();
        driver.findElement(textDescriptionArea).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        driver.findElement(textDescriptionArea).sendKeys(Keys.BACK_SPACE);
        clickSaveDescriptionButton();
    }

    public String getDescriptionText() {
        return driver.findElement(descriptionArea).getText();
    }

    private void clickAddDescriptionButton() {
        driver.findElement(DescriptionButton).click();
    }

    private void sendTextToDescriptionArea(String text) {
        driver.findElement(textDescriptionArea).sendKeys(text);
    }

    private void clickSaveDescriptionButton() {
        driver.findElement(saveDescriptionButton).click();
    }

    private void clickLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }


}

