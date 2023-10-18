import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.NewItemPage;

import static org.testng.Assert.*;

public class NewItemPageTest extends BaseTest {

    @Test
    public void testNameFieldIfEmpty() {
        NewItemPage newItemPage = getHomePage().goToNewItemPage();
        newItemPage.clickOkButton();
        String actual = newItemPage.getHelpMessage();
        String expected = "» This field cannot be empty, please enter a valid name";
        assertEquals(actual, expected);
    }

    @Test
    public void testNameFieldWithValidCharacters() {
        NewItemPage newItemPage = getHomePage().goToNewItemPage();
        newItemPage.fillOutNameField("project");
        String actual = newItemPage.getHelpMessage();
        String expected = "» Required field";
        assertEquals(actual, expected);
    }

    /**
     * Preconditions:
     * - job with the name using as a parameter must be already created
     * Note that name is case-insensitive
     */

    @Test
    public void testNameFieldIfJobWithThatNameExists() {
        createNewProject("Test Project", NewItemPage.ItemTypes.FREESTYLE_PROJECT);
        backToHomePage();
        NewItemPage newItemPage = getHomePage().goToNewItemPage();
        newItemPage.fillOutNameField("Test Project");
        newItemPage.clickOkButton();
        String actual = newItemPage.getHelpMessage();
        String expected = "» A job already exists with the name ‘Test Project’";
        assertEquals(actual, expected);
    }

    @Test(dataProvider = "invalidNames")
    public void testNameFieldWithInValidCharacters(String name) {
        NewItemPage newItemPage = getHomePage().goToNewItemPage();
        newItemPage.fillOutNameField(name);
        String actual = newItemPage.getHelpMessage();
        assertTrue(actual.contains("is an unsafe character"));
    }

    @Test
    public void testIfItemCopyOptionOnWithJobsExisted() {
        createNewProject("Test Project", NewItemPage.ItemTypes.FREESTYLE_PROJECT);
        backToHomePage();
        boolean result = getHomePage()
                .goToNewItemPage()
                .isItemCopyAreaDisplayed();
        assertTrue(result);
    }

    @Test
    public void testIfItemCopyOptionOnWithoutJobsExisted() {
        boolean result = getHomePage()
                .goToNewItemPage()
                .isItemCopyAreaDisplayed();
        assertFalse(result);
    }

    @DataProvider(name = "invalidNames")
    public Object[][] invalidNamesData() {
        return new Object[][]{
                {"!FreestyleProject"},
                {"#FreestyleProject"},
                {"$FreestyleProject"},
                {"&FreestyleProject"},
                {"/FreestyleProject"},
        };
    }
}
