package steps;

import org.openqa.selenium.*;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class BasePage {

    private  JavascriptExecutor js;
    private WebDriver driver;
    private Wait<WebDriver> wait;

    protected BasePage(WebDriver driver) {
        this.js = (JavascriptExecutor) driver;
        this.driver = driver;
        this.wait = new FluentWait <WebDriver>(this.driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }

    protected WebElement findElement(By locator){
        return (WebElement) wait.until(new Function<WebDriver, WebElement>(){
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }

    protected List<WebElement> findElements(By locator){
        return (List<WebElement>) wait.until(new Function<WebDriver, List<WebElement>>(){
            @Override
            public List<WebElement> apply(WebDriver driver) {
                return driver.findElements(locator);
            }
        });
    }

    protected void waitUntilElementVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void click(By locator){
        findElement(locator).click();
    }

    protected String getText(By locator){
        return findElement(locator).getText();
    }


    protected void sendKeys(By locator, CharSequence text){
        findElement(locator).sendKeys(text);
    }


    protected void clear(By locator) {
        findElement(locator).clear();
    }


    protected String getAttribute(By locator,String name){
        return findElement(locator).getAttribute(name);
    }

    public void driverWait(int miliSeconds){
        try {
            synchronized (driver)
            {
                driver.wait(miliSeconds);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void scrollToElement(By by) {

        js.executeScript("window.scrollBy(0,2400)", "");
    }
    public void scrollToElement2(By by) {

        js.executeScript("window.scrollBy(0,2000)", "");
    }


    public void scrollElement(By element) throws InterruptedException {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }

    public void scrollDown(By locater) {


        js.executeScript("arguments[0].scrollIntoView(true);", findElement(locater));
    }

    public void doubleClick(By locater){
    Actions actions = new Actions(driver);
    actions.doubleClick((WebElement) locater).build().perform();
}

    public String getTitle() {
        return driver.getTitle();
    }

    protected void switchNewTab(String window){
        Set<String> handles = driver.getWindowHandles();
        for (String windowsHandle:handles
             ) {
            if (!window.equals(windowsHandle)){
                driver.switchTo().window(windowsHandle);
            }
        }
    }
}
