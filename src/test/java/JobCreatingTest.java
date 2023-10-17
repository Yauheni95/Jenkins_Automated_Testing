import org.testng.annotations.Test;
import pages.HomePage;
import pages.NewItemPage;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

public class JobCreatingTest extends BaseTest {

    @Test
    public void testSuccessfulCreatingOfNewJob() {
        HomePage homePage = getHomePage();
        int initProjectsAmount = homePage.getAllProjectsNames().size();
        homePage
                .goToNewItemPage()
                .createJob("Test Project", NewItemPage.ItemTypes.FREESTYLE_PROJECT);
        backToHomePage();
        int resultProjectsAmount = homePage.getAllProjectsNames().size();
        assertNotEquals(resultProjectsAmount, initProjectsAmount);
        assertTrue(homePage.isProjectExist("Test Project"));


    }
}
