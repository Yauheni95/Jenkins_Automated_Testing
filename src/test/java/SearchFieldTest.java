import org.testng.annotations.Test;
import pages.NewItemPage;
import pages.commonElements.MutualPageContent;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SearchFieldTest extends BaseTest {

    @Test
    public void testHotKeysSearchFieldSelection() {
        getHomePage().getMutualPageContent().selectSearchFieldByHotKeys();
        boolean result = getHomePage().getMutualPageContent().isSearchFieldSelected();
        assertTrue(result);
    }

    @Test
    public void testSearchingWithoutResultsExpected() {
        MutualPageContent content = getHomePage().getMutualPageContent();
        content.searchOnJenkins("Incorrect search request");
        boolean result = content.isResultFound();
        assertFalse(result);
    }


    /**
     * Job with the name using in the searching request must exist
     */
    @Test
    public void testSearchingWithExactMatch() {
        createNewProject("Test Project", NewItemPage.ItemTypes.FREESTYLE_PROJECT);
        final String searchRequest = "Test Project";
        MutualPageContent content = getHomePage().getMutualPageContent();
        content.searchOnJenkins(searchRequest);
        assertTrue(content.isResultFound());
        assertTrue(content.isExactMatchResult(searchRequest));
    }

    @Test(groups = "someJobsExist")
    public void testSearchingWithNonExactMatch() {
        createNewProject("Test Project", NewItemPage.ItemTypes.FREESTYLE_PROJECT);
        final String searchRequest = "Test";
        MutualPageContent content = getHomePage().getMutualPageContent();
        content.searchOnJenkins(searchRequest);
        assertTrue(content.isResultFound());
        assertFalse(content.isExactMatchResult(searchRequest));
    }
}
