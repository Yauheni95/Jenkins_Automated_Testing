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
    protected HomePage getHomePage() {
        return homePage;
    }

    protected void createNewProject(String name, NewItemPage.ItemTypes type) {
        getHomePage()
                .goToNewItemPage()
                .createJob(name, type);
    }

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
    protected void refreshAll() {
        backToHomePage();
        getHomePage().getProjectPanel().deleteAllProjects();
        getHomePage().clearDescriptionArea();
    }

    protected void backToHomePage() {
        getHomePage().getMutualPageContent().clickHomePageButton();
    }


}
