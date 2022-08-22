package protein.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {
    By mainTabs = By.cssSelector("nav[class='Navigation'] a");
    By ourTechnologiesLink = By.cssSelector("[href='#OurTechnologies']");
    By whoWeAreLink = By.cssSelector("[href='#WhoWeAre']");
    By pTalks = By.cssSelector("[href='#PTalks']");
    By sayHey = By.cssSelector("[href='#SayHey']");
    By ourTechnologiesSection = By.id("OurTechnologies");
    By openRulesSection = By.cssSelector("section[class^='OpenRules_OpenRules']");
    By softwareDevelopmentOpenRules = By.xpath("//b[text() = 'Software Development']//..//div[contains(@class, 'OpenRules_RuleList')]//a");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getOpenRules() {
        return findElements(softwareDevelopmentOpenRules);
    }

    public List<WebElement> getMainTabs() {
        return findElements(mainTabs);
    }

    public void clickMainTabByLinkText(String linkText) {
        for (WebElement element :
                getMainTabs()) {
            if (element.getText().equalsIgnoreCase(linkText)) {
                clickElement(element);
            }
        }
    }

    public int getMainTabCount() {
        return getMainTabs().size();
    }

    public boolean isOurTechnologiesSectionOpen() {
        waitBySec(2);
        return visibilityOfElementLocated(ourTechnologiesSection) != null && findElement(ourTechnologiesLink).getAttribute("active").equals("active");
    }

    public boolean isOpenRolesSectionOpen() {
        waitBySec(3);
        return visibilityOfElementLocated(whoWeAreLink) != null && findElement(whoWeAreLink).getAttribute("active").equals("active");
    }

    public void scrollToOpenRolesSection() {
        scrollToElement(findElement(openRulesSection));
    }

    public boolean checkThePositionUnderTheSoftwareDevelopmentSection(String role) {
        for (WebElement element :
                getOpenRules()) {
            if (element.getText().equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }

    public void clickTheRoleByTitle(String openRole) {
        for (WebElement element :
                getOpenRules()) {
            if (element.getText().equalsIgnoreCase(openRole)) {
                clickElementWithJs(element);
            }
        }
    }
}
