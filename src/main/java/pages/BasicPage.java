package pages;

import org.openqa.selenium.WebDriver;
import pages.commonElements.MutualPageContent;

public abstract class BasicPage {
    protected WebDriver driver;
    private final MutualPageContent mutualPageContent;
    public BasicPage(WebDriver driver) {
        this.driver = driver;
        mutualPageContent=new MutualPageContent(driver);
    }

    public MutualPageContent getMutualPageContent() {
        return mutualPageContent;
    }
}
