package com.automation.commons;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;

public class WebUI{
    private static final Logger log = LoggerFactory.getLogger(WebUI.class);
    private static WebDriver driver;
    private static int TIME_OUT = 5;
    private static int PAGE_LOAD_TIMEOUT = 50;

    public WebUI(WebDriver driver) {
        this.driver = driver;
    }

    public static void logConsole(String message) {
        System.out.println(message);
    }

    public static void sleep(long m){
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitElementTobeVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }catch (Throwable e) {
            System.out.println("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitElementTobeVisible(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }catch (Throwable e) {
            System.out.println("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitElementToBeClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable e) {
            System.out.println("Timeout waiting for the element to be clickable. " + by.toString());
            Assert.fail("Timeout waiting for the element to be clickable. " + by.toString());
        }
    }

    public static void waitElementToBeClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable e) {
            System.out.println("Timeout waiting for the element to be clickable. " + by.toString());
            Assert.fail("Timeout waiting for the element to be clickable. " + by.toString());
        }
    }

    //Chờ đợi trang load xong mới thao tác
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Wait for Javascript to load
        ExpectedCondition< Boolean > jsLoad = new ExpectedCondition<Boolean> () {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            //System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }

    public static void verifyElementIsDisplayed(By by) {
        waitElementTobeVisible(by);
        logConsole("Verify element is displayed: " + by);
        Assert.assertTrue(driver.findElement(by).isDisplayed(), "Element is not displayed: " + by);
        logConsole("Element is displayed: " + by);
    }

    public static void clickElement(By by) {
        waitElementToBeClickable(by);
        logConsole("Click element: " + by);
        driver.findElement(by).click();
        logConsole("Click element completed");
    }

    public static void setText(By by, String text) {
        logConsole("Set text in element: " + by);
        logConsole("Text: " + text);
        waitElementTobeVisible(by);
        driver.findElement(by).sendKeys(text);
        logConsole("Set text completed");
    }

    public static String getText(By by) {
        waitElementTobeVisible(by);
        logConsole("Get text in element: " + by);
        String text = driver.findElement(by).getText();
        logConsole("Text: " + text);
        logConsole("Get text completed");
        return text;
    }

    public static void clearText(By by) {
        waitElementTobeVisible(by);
        logConsole("Clear text in element: " + by);
        driver.findElement(by).clear();
        logConsole("Clear text completed");
    }

    public static boolean verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        System.out.println("Verify equals: " + actual + " and " + expected);
        boolean check = actual.equals(expected);
        return check;
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        System.out.println("Assert equals: " + actual + " and " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    public static boolean verifyContains(String actual, String expected) {
        waitForPageLoaded();
        System.out.println("Verify contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        return check;
    }

    public static void assertContains(String actual, String expected, String message) {
        waitForPageLoaded();
        System.out.println("Assert contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        Assert.assertTrue(check, message);
    }
}
