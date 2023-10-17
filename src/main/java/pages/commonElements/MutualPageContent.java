package pages.commonElements;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import pages.HomePage;

import java.util.List;
import java.util.stream.Collectors;

public class MutualPageContent {
    private final WebDriver driver;

    public MutualPageContent(WebDriver driver) {
        this.driver = driver;
    }

    private final By jenkinsHomePageLink = By.id("jenkins-name-icon");
    private final By searchBox = By.id("search-box");
    private final By administrativeMonitorsInfo = By.xpath("//a[@id='visible-am-button']");
    private final By administrativeSecurityMonitorsInfo = By.xpath("//a[@id='visible-sec-am-button']");
    private final By userMenuButton = By.xpath("(//a[@class='model-link'])[1]");
    private final By userDropDownMenuButton = By.xpath("(//button[@class='jenkins-menu-dropdown-chevron'])[1]");
    private final By logOutLink = By.linkText("log out");
    private final By restApiLink = By.linkText("REST API");
    private final By jenkinsVersionDropDownButton = By.xpath("//*[@class='jenkins-button jenkins-button--tertiary jenkins_ver']");
    private final By searchResults = By.xpath("//div[@id='main-panel']//li");
    private final By searchErrorField = By.className("error");

    public HomePage clickHomePageButton() {
        driver.findElement(jenkinsHomePageLink).click();
        return new HomePage(driver);
    }

    public void selectSearchFieldByHotKeys() {
        new Actions(driver).keyDown(Keys.CONTROL).sendKeys("k").keyUp(Keys.CONTROL).perform();
    }

    public void searchOnJenkins(String text) {
        WebElement searchField = driver.findElement(searchBox);
        searchField.sendKeys(text);
        searchField.sendKeys(Keys.ENTER);
    }

    public boolean isResultFound() {
        return driver.findElements(searchErrorField).isEmpty();
    }

    public boolean isExactMatchResult(String text) {
        return driver.getTitle().equals(text + " [Jenkins]");
    }

    public boolean isSearchFieldSelected() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement focusedElement = (WebElement) js.executeScript("return document.activeElement;");
        return focusedElement.getAttribute("id").equals("search-box");
    }

    public List<String> getListOfResults() {
        return driver.findElements(searchResults).stream().map(WebElement::getText).collect(Collectors.toList());
    }

}

