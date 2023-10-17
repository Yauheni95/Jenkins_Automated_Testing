import org.testng.annotations.*;

import static org.testng.Assert.*;

public class HomePageTest extends BaseTest {

    @BeforeGroups(groups = "textDescriptionTest")
    public void prepareDescriptionArea() {
        getHomePage().clearDescriptionArea();
    }

    @AfterGroups(groups = "textDescriptionTest")
    public void clearDescription() {
        getHomePage().clearDescriptionArea();
    }

    @Test(groups = "textDescriptionTest")
    public void testAddDescription() {
        String expected = "Testing description feature on Jenkins Home Page";
        getHomePage().addDescription(expected);
        String actual = getHomePage().getDescriptionText();
        assertEquals(actual, expected);
    }

    @Test(groups = "textDescriptionTest")
    public void clearDescriptionArea() {
        getHomePage().addDescription("Test text");
        getHomePage().clearDescriptionArea();
        String actual = getHomePage().getDescriptionText();
        assertTrue(actual.isEmpty());
    }

    @Test(groups = "textDescriptionTest")
    public void testDescriptionButtonWhenTextExists() {
        getHomePage().addDescription("Test text");
        String actual = getHomePage().getDescriptionButtonText();
        String expected = "Edit description";
        assertEquals(actual, expected);
    }

    @Test
    public void testHomePageWithoutProjectsCreated() {
        assertTrue(getHomePage().isWelcomeMessageDisplayed());
        assertTrue(getHomePage().getAllProjectsNames().isEmpty());
    }

    @Test (groups = "someJobsExist")
    public void testHomePageWithProjectsCreated() {
        assertFalse(getHomePage().isWelcomeMessageDisplayed());
        assertFalse(getHomePage().getAllProjectsNames().isEmpty());
    }


}
