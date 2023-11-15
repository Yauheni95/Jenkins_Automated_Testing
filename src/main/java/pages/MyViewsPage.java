package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyViewsPage extends BasicPage{

    private static final By dropDownProjectButton = By.xpath("//button[@class='jenkins-menu-dropdown-chevron' and contains(@data-href, 'job')]");

    private static final By deleteProjectButton = By.xpath("//button[contains(@href,'doDelete')]");

    private static final By projectLink = By.xpath("//a[@class='jenkins-table__link model-link inside']");

    public MyViewsPage(WebDriver driver) {
        super(driver);
    }
}
