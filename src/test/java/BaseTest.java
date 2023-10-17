import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.NewItemPage;
import support.JenkinsUtils;

import java.time.Duration;

public class BaseTest {
    private WebDriver driver;
    private HomePage homePage;

    @BeforeClass
    protected void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://localhost:8080/");
        homePage = new HomePage(driver);
        JenkinsUtils.logIn(driver);
    }

    @AfterClass
    protected void tierDown() {
        JenkinsUtils.logOut(driver);
        driver.quit();
    }

    @BeforeMethod
    protected void backToHomePage() {
        getHomePage().getMutualPageContent().clickHomePageButton();
    }

    @BeforeGroups("someJobsExist")
    protected void createJobToTest() {
        getHomePage()
                .goToNewItemPage()
                .createJob("Test Project", NewItemPage.ItemTypes.FREESTYLE_PROJECT);
        backToHomePage();
        getHomePage()
                .goToNewItemPage()
                .createJob("Test Project 2", NewItemPage.ItemTypes.FREESTYLE_PROJECT);
        backToHomePage();
    }

    @AfterGroups("someJobsExist")
    protected void deleteJob() {
        backToHomePage();
        getHomePage().deleteJobByName("Test Project");
        getHomePage().deleteJobByName("Test Project 2");
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected HomePage getHomePage() {
        return homePage;
    }
}
