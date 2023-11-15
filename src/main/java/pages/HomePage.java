package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.commonElements.ProjectPanel;

public class HomePage extends BasicPage {
    private ProjectPanel projectPanel;
    private static final By DescriptionButton = By.id("description-link");

    private static final By textDescriptionArea = By.name("description");

    private static final By descriptionArea = By.cssSelector("#description > div:nth-child(1)");

    private static final By saveDescriptionButton = By.name("Submit");

    private static final By welcomeJenkinsTextMessage = By.xpath("//h1[text() = 'Welcome to Jenkins!']");

    public HomePage(WebDriver driver) {
        super(driver);
    }
    public ProjectPanel getProjectPanel() {
        if (projectPanel==null) projectPanel= new ProjectPanel(driver);
        return projectPanel;
    }

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

    public String getDescriptionButtonText() {
        return driver.findElement(DescriptionButton).getText();
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

