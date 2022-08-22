package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.junit.Assert;
import protein.pages.HomePage;

public class HomeStep extends BaseStep {

    HomePage homePage;

    @Before
    public void setUp() {
        homePage = new HomePage(driver);
    }

    @After
    public void quit() {
        driver.quit();
    }

    @Given("^Navigating to \"(.*?)\" web page$")
    public void navigating_to_web_page(String url) {
        homePage.openUrl(url);
    }

    @Given("^Checking the (\\d+) main tab sections on the home page$")
    public void checking_the_main_tab_sections_on_the_home_page(int arg) {
        Assert.assertTrue(homePage.getMainTabCount() == arg);
    }

    @Given("^Clicking on the \"(.*?)\" tab$")
    public void clicking_on_the_tab(String linkText) {
        homePage.clickMainTabByLinkText(linkText);
    }

    @Given("^Verifying that the relevant section is open$")
    public void verifying_that_the_relevant_section_is_open() {
        Assert.assertTrue(homePage.isOurTechnologiesSectionOpen());
    }

    @Given("^Scrolling the page down and making sure the Open Roles section is present$")
    public void scrolling_the_page_down_and_making_sure_the_Open_Roles_section_is_present() {
        homePage.scrollToOpenRolesSection();
        Assert.assertTrue(homePage.isOpenRolesSectionOpen());
    }

    @Given("^Checking if the \"(.*?)\" position is situated under the Software Development section$")
    public void checking_if_the_position_is_situated_under_the_Software_Development_section(String openRole) {
        Assert.assertTrue(homePage.checkThePositionUnderTheSoftwareDevelopmentSection(openRole));
        homePage.waitBySec(3);
    }

    @Given("^Clicking on the \"(.*?)\" position and verifying that the new tab is open$")
    public void clicking_on_the_position_and_verifying_that_the_new_tab_is_open(String openRole) {
        homePage.clickTheRoleByTitle(openRole);
        homePage.waitBySec(2);
        Assert.assertTrue(homePage.getWindowsCount() == 2);
    }
}
