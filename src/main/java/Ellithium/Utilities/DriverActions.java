package Ellithium.Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class DriverActions {
    public static void SendData(WebDriver driver, By locator, String data, int timeout, int pollingEvery) {
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEvery))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).clear();
        logsUtils.info(Colors.BLUE+"Sending Data \""+ data+"\" To Element: "+locator+Colors.RESET);
        driver.findElement(locator).sendKeys(data);
    }

    public static void SendData(WebDriver driver, By locator, Keys data, int timeout, int pollingEvery) {
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEvery))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).clear();
        logsUtils.info(Colors.BLUE+"Sending Data \""+ data+"\" To Element: "+locator+Colors.RESET);
        driver.findElement(locator).sendKeys(data);
    }

    public static String getText(WebDriver driver, By locator, int timeout, int pollingEvery) {
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEvery))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        logsUtils.info(Colors.BLUE+"Get Text From Element: "+locator+Colors.RESET);
        return driver.findElement(locator).getText();
    }

    public static void ClickingOnElement(WebDriver driver, By locator, int timeout, int pollingEvery) {
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEvery))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
        logsUtils.info(Colors.BLUE+"Click On Element: "+locator+Colors.RESET);
    }

    public static void waitForInvisibility(WebDriver driver, By locator, int timeout, int pollingEvery) {
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEvery))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
        logsUtils.info(Colors.BLUE+"Waiting for invisibility Of Element Located: "+locator+Colors.RESET);
    }

    // General wait
    public static WebDriverWait generalWait(WebDriver driver, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    // Scroll to an element
    public static void scrollToElement(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findWebelement(driver, locator));
        logsUtils.info(Colors.BLUE+"Scrolling To Element Located: "+locator+Colors.RESET);
    }

    // Get a timestamp
    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }

    // Select dropdown option by visible text
    public static void selectDropdownByText(WebDriver driver, By locator, String option, int timeout, int pollingEvery) {
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEvery))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        logsUtils.info(Colors.BLUE+"Select Dropdown Option By Text: "+option+" From Element: "+locator+Colors.RESET);
        new Select(findWebelement(driver, locator)).selectByVisibleText(option);
    }

    // Select dropdown option by value
    public static void selectDropdownByValue(WebDriver driver, By locator, String value, int timeout, int pollingEvery) {
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEvery))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        logsUtils.info(Colors.BLUE+"Select Dropdown Option By Value: "+value+" From Element: "+locator+Colors.RESET);
        new Select(findWebelement(driver, locator)).selectByValue(value);
    }

    // Select dropdown option by index
    public static void selectDropdownByIndex(WebDriver driver, By locator, int index, int timeout, int pollingEvery) {
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEvery))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        logsUtils.info(Colors.BLUE+"Select Dropdown Option By Index: "+index+" From Element: "+locator+Colors.RESET);
        new Select(findWebelement(driver, locator)).selectByIndex(index);
    }

    // JavaScript click on an element
    public static void javascriptClick(WebDriver driver, By locator) {
        WebElement element = findWebelement(driver, locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        logsUtils.info(Colors.BLUE+"JavaScript Click On Element: "+locator+Colors.RESET);
    }

    // Wait for an element to disappear
    public static void waitForElementToDisappear(WebDriver driver, By locator, int timeout, int pollingEvery) {
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEvery))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
        logsUtils.info(Colors.BLUE+"Waiting for Element To Disappear: "+locator+Colors.RESET);
    }

    // Get attribute value from an element
    public static String getAttributeValue(WebDriver driver, By locator, String attribute, int timeout, int pollingEvery) {
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEvery))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        logsUtils.info(Colors.BLUE+"Getting Attribute: "+attribute+" From Element: "+locator+Colors.RESET);
        return driver.findElement(locator).getAttribute(attribute);
    }

    // Sleep for a specified number of milliseconds
    public static void sleepMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            logsUtils.error("Sleep interrupted: "+ e.getMessage());
        }
    }

    // Sleep for a specified number of seconds
    public static void sleepSeconds(long seconds) {
        sleepMillis(seconds * 1000);
    }

    // Sleep for a specified number of minutes
    public static void sleepMinutes(long minutes) {
        sleepMillis(minutes * 60 * 1000);
    }

    // Set implicit wait
    public static void setImplicitWait(WebDriver driver, int timeout) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        logsUtils.info(Colors.BLUE+"Set Implicit Wait To: "+timeout+" seconds"+Colors.RESET);
    }

    // Explicit wait for element to be clickable
    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator, int timeout, int pollingEvery) {
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEvery))
                .until(ExpectedConditions.elementToBeClickable(locator));
        logsUtils.info(Colors.BLUE+"Wait For Element To Be Clickable: "+locator+Colors.RESET);
        return driver.findElement(locator);
    }

    // Explicit wait for element to be visible
    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator, int timeout, int pollingEvery) {
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEvery))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        logsUtils.info(Colors.BLUE+"Wait For Element To Be Visible: "+locator+Colors.RESET);
        return driver.findElement(locator);
    }

    // Explicit wait for element to be present in the DOM
    public static WebElement waitForElementPresence(WebDriver driver, By locator, int timeout, int pollingEvery) {
        new FluentWait<>(driver).withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingEvery))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        logsUtils.info(Colors.BLUE+"Wait For Element Presence: "+locator+Colors.RESET);
        return driver.findElement(locator);
    }

    // Navigate to a URL
    public static void navigateToUrl(WebDriver driver, String url) {
        driver.get(url);
        logsUtils.info(Colors.BLUE+"Navigated To URL: "+url+Colors.RESET);
    }

    // Refresh the current page
    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
        logsUtils.info(Colors.BLUE+"Page Refreshed"+Colors.RESET);
    }

    // Navigate back in the browser history
    public static void navigateBack(WebDriver driver) {
        driver.navigate().back();
        logsUtils.info(Colors.BLUE+"Navigated Back In Browser History"+Colors.RESET);
    }

    // Navigate forward in the browser history
    public static void navigateForward(WebDriver driver) {
        driver.navigate().forward();
        logsUtils.info(Colors.BLUE+"Navigated Forward In Browser History"+Colors.RESET);
    }

    // Switch to a new window or tab
    public static void switchToNewWindow(WebDriver driver, String windowTitle) {
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().equals(windowTitle)) {
                logsUtils.info(Colors.BLUE+"Switched To New Window: "+windowTitle+Colors.RESET);
                return;
            }
        }
        driver.switchTo().window(originalWindow);
        logsUtils.info(Colors.RED+"Window with title "+windowTitle+" not found. Switched back to original window."+Colors.RESET);
    }

    // Close the current window or tab
    public static void closeCurrentWindow(WebDriver driver) {
        driver.close();
        logsUtils.info(Colors.BLUE+"Closed Current Window"+Colors.RESET);
    }

    // Switch to the original window
    public static void switchToOriginalWindow(WebDriver driver, String originalWindowHandle) {
        driver.switchTo().window(originalWindowHandle);
        logsUtils.info(Colors.BLUE+"Switched To Original Window"+Colors.RESET);
    }
    // Overloaded SendData method with pollingEvery 500ms
    public static void SendData(WebDriver driver, By locator, String data) {
        SendData(driver, locator, data, 5, 500); // Timeout 5 seconds, pollingEvery 500ms
    }

    public static void SendData(WebDriver driver, By locator, Keys data) {
        SendData(driver, locator, data, 5, 500); // Timeout 5 seconds, pollingEvery 500ms
    }

    // Overloaded getText method with pollingEvery 500ms
    public static String getText(WebDriver driver, By locator) {
        return getText(driver, locator, 5, 500); // Timeout 5 seconds, pollingEvery 500ms
    }

    // Overloaded ClickingOnElement method with pollingEvery 500ms
    public static void ClickingOnElement(WebDriver driver, By locator) {
        ClickingOnElement(driver, locator, 5, 500); // Timeout 5 seconds, pollingEvery 500ms
    }

    // Overloaded waitForInvisibility method with pollingEvery 500ms
    public static void waitForInvisibility(WebDriver driver, By locator) {
        waitForInvisibility(driver, locator, 5, 500); // Timeout 5 seconds, pollingEvery 500ms
    }

    // Overloaded selectDropdownByText method with pollingEvery 500ms
    public static void selectDropdownByText(WebDriver driver, By locator, String option) {
        selectDropdownByText(driver, locator, option, 5, 500); // Timeout 5 seconds, pollingEvery 500ms
    }

    // Overloaded selectDropdownByValue method with pollingEvery 500ms
    public static void selectDropdownByValue(WebDriver driver, By locator, String value) {
        selectDropdownByValue(driver, locator, value, 5, 500); // Timeout 5 seconds, pollingEvery 500ms
    }

    // Overloaded selectDropdownByIndex method with pollingEvery 500ms
    public static void selectDropdownByIndex(WebDriver driver, By locator, int index) {
        selectDropdownByIndex(driver, locator, index, 5, 500); // Timeout 5 seconds, pollingEvery 500ms
    }

    // Overloaded waitForElementToBeClickable method with pollingEvery 500ms
    public static WebElement waitForElementToBeClickable(WebDriver driver, By locator) {
        return waitForElementToBeClickable(driver, locator, 5, 500); // Timeout 5 seconds, pollingEvery 500ms
    }

    // Overloaded waitForElementToBeVisible method with pollingEvery 500ms
    public static WebElement waitForElementToBeVisible(WebDriver driver, By locator) {
        return waitForElementToBeVisible(driver, locator, 5, 500); // Timeout 5 seconds, pollingEvery 500ms
    }

    // Overloaded waitForElementPresence method with pollingEvery 500ms
    public static WebElement waitForElementPresence(WebDriver driver, By locator) {
        return waitForElementPresence(driver, locator, 5, 500); // Timeout 5 seconds, pollingEvery 500ms
    }

    // Overloaded waitForElementToDisappear method with pollingEvery 500ms
    public static void waitForElementToDisappear(WebDriver driver, By locator) {
        waitForElementToDisappear(driver, locator, 5, 500); // Timeout 5 seconds, pollingEvery 500ms
    }

    // Overloaded getAttributeValue method with pollingEvery 500ms
    public static String getAttributeValue(WebDriver driver, By locator, String attribute) {
        return getAttributeValue(driver, locator, attribute, 5, 500); // Timeout 5 seconds, pollingEvery 500ms
    }
    // Find an element
    public static WebElement findWebelement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }


}