package protein.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    WebDriver driver;
    JavascriptExecutor jse;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, 15);
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void closeBrowser() {
        driver.quit();
    }

    public void switchToWindowByIndex(int index) {
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[index]);
    }

    public int getWindowsCount() {
        return driver.getWindowHandles().size();
    }

    protected void sendKeys(By by, String value) {
        WebElement element = waitForElementLocated(by);
        scrollToElement(element);
        element.clear();
        element.sendKeys(value);
    }

    protected void sendKeys(WebElement element, String value) {
        scrollToElement(element);
        element.clear();
        element.sendKeys(value);
    }

    protected WebElement findElement(By by) {
        return waitForElementLocated(by);
    }

    protected List<WebElement> findElements(By by) {
        waitForElementLocated(by);
        return driver.findElements(by);
    }

    protected void selectByText(By by, String text) {
        Select select = new Select(findElement(by));
        select.selectByVisibleText(text);
    }

    protected void clickElement(By by) {
        scrollToElement(waitForElementLocated(by));
        findElement(by).click();
    }

    protected void clickElement(WebElement element) {
        scrollToElement(element);
        element.click();
    }

    protected WebElement elementToBeClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected String getText(By by) {
        waitForElementLocated(by);
        scrollToElement(findElement(by));
        return findElement(by).getAttribute("value") != null ? findElement(by).getAttribute("value") : findElement(by).getText();
    }

    protected void scrollToElement(WebElement element) {
        jse.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickElementWithJs(WebElement element) {
        jse.executeScript("arguments[0].click();", element);
    }

    public void waitBySec(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected WebElement waitForElementLocated(By by) {
        return visibilityOfElementLocated(by);

    }

    protected WebElement visibilityOfElementLocated(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}