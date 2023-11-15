package pages;

import org.openqa.selenium.WebDriver;
import pages.commonElements.MutualPageContent;

public abstract class BasicPage {

    protected WebDriver driver;
    private MutualPageContent mutualPageContent;

    public BasicPage(WebDriver driver) {
        this.driver = driver;

    }
    public MutualPageContent getMutualPageContent() {
        if (mutualPageContent == null)
            mutualPageContent = new MutualPageContent(driver);
        return mutualPageContent;
    }
}
