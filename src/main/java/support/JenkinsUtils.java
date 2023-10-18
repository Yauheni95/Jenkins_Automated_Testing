package support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JenkinsUtils {
    private static final String LOGIN = AppConfig.getUsername();
    private static final String PASSWORD = AppConfig.getPassword();
    private final static By loginField = org.openqa.selenium.By.xpath("//input[@id='j_username']");
    private final static By passwordField = By.xpath("//input[@id='j_password']");
    private final static By signInButton = By.xpath("//button[@name='Submit']");
    private final static By logOutLink = By.xpath("//a[@href='/logout']");


    public static void logIn(WebDriver driver) {
        driver.findElement(loginField).sendKeys(LOGIN);
        driver.findElement(passwordField).sendKeys(PASSWORD);
        driver.findElement(signInButton).click();
    }

    public static void logOut(WebDriver driver) {
        driver.findElement(logOutLink).click();
    }

}
