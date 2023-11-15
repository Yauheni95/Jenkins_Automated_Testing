package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewItemPage extends BasicPage {

    private static final By inputNameField = By.id("name");

    private static final By okButton = By.id("ok-button");

    private static final By copyItemArea = By.className("item-copy");

    private static final By helpMessage = By.xpath("//div[@class='add-item-name']//div[not(contains(@class,'disabled'))]");

    public NewItemPage(WebDriver driver) {
        super(driver);
    }

    public enum ItemTypes {
        FREESTYLE_PROJECT(org.openqa.selenium.By.xpath("//li[contains(@class, 'FreeStyleProject')]")),
        PIPELINE(org.openqa.selenium.By.xpath("//li[contains(@class, 'WorkflowJob')]")),
        MULTI_CONFIGURATION_PROJECT(By.xpath("//li[contains(@class, 'MatrixProject')]")),
        FOLDER(By.xpath("//li[contains(@class, 'Folder')]")),
        MULTIBRANCH_PIPELINE(By.xpath("//li[contains(@class, 'WorkflowMultiBranchProject')]")),
        ORGANIZATION_FOLDER(By.xpath("//li[contains(@class, 'OrganizationFolder')]"));

        private final By locator;

        ItemTypes(By locator) {
            this.locator = locator;
        }

        public By getLocator() {
            return locator;
        }
    }

    public void selectProjectOption(ItemTypes type) {
        driver.findElement(type.getLocator()).click();
    }

    public boolean isSelected(ItemTypes type) {
        return Boolean.parseBoolean(driver.findElement(type.getLocator()).getAttribute("aria-checked"));
    }

    public void fillOutNameField(String text) {
        driver.findElement(inputNameField).sendKeys(text);
    }

    public void clickOkButton() {
        driver.findElement(okButton).click();
    }

    public void createJob(String itemName, ItemTypes type) {
        fillOutNameField(itemName);
        selectProjectOption(type);
        clickOkButton();
    }

    public boolean isItemCopyAreaDisplayed() {
        return !driver.findElements(copyItemArea).isEmpty();
    }


    public String getHelpMessage() {
        return driver.findElement(helpMessage).getText();
    }
}



