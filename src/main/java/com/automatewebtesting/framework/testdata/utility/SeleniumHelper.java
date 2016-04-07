/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.automatewebtesting.framework.testdata.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author h.das
 */
public class SeleniumHelper {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void openWebApp(String url) {
        openWebApp(url, 0);
    }

    public void openWebApp(String url, long sleepTime) {
        if (!isBrowserOpen()) {
            launchBrowser();
        }
        sleep(sleepTime);
        driver.get(url);
    }

    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Automation\\NewConnect2Yes\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();

        // driver = new FirefoxDriver();
        maximizeBrowser();
    }

    public void maximizeBrowser() {
        driver.manage().window().maximize();
    }

    public WebElement findElementById(String elementId) {
        return driver.findElement(By.id(elementId));
    }

    public WebElement findElementByXPath(String xPath) {
        return driver.findElement(By.xpath(xPath));
    }

    public void clickElementById(String elementId) {
        findElementById(elementId).click();
    }

    public void clickElementByXPath(String xPath) {
        findElementByXPath(xPath).click();
    }

    public void inputTextOnElementById(String elementId, String text) {
        findElementById(elementId).sendKeys(text);
    }

    public void inputTextOnElementByXPath(String xPath, String text) {
        findElementByXPath(xPath).sendKeys(text);
    }

    public String getTextFromElementById(String elementId) {
        return findElementById(elementId).getText();
    }

    public String getTextFromElementByXPath(String xPath) {
        return findElementByXPath(xPath).getText();
    }

    public boolean isElementDisplayedById(String elementId) {
        return findElementById(elementId).isDisplayed();
    }

    public boolean isElementDisplayedByXPath(String xPath) {
        return findElementByXPath(xPath).isDisplayed();
    }

    public void dispose() {
        if (isBrowserOpen()) {
            driver.quit();
        }
    }

    public void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    private boolean isBrowserOpen() {
        return !hasDriverQuit();
    }

    private boolean hasDriverQuit() {
        return ((driver == null) || driver.toString().contains("null"));
    }
}
