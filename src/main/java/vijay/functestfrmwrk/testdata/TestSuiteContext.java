/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vijay.functestfrmwrk.testdata;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author h.das
 */
class TestSuiteContext {

    private Map<String, Object> suiteWideInfoMap;
    private static Map<Long, TestSuiteContext> threadWiseInstanceMap;

    static {
        threadWiseInstanceMap = new ConcurrentHashMap<Long, TestSuiteContext>();
    }

    public static TestSuiteContext getInstance() {
        if (!isThreadInstancePresent()) {
            createNewInstance();
        }
        return returnInstance();
    }

    private static boolean isThreadInstancePresent() {
        return threadWiseInstanceMap.containsKey(getCurrentThreadId());
    }

    private static void createNewInstance() {
        threadWiseInstanceMap.put(getCurrentThreadId(), new TestSuiteContext());
    }

    private static TestSuiteContext returnInstance() {
        return threadWiseInstanceMap.get(getCurrentThreadId());
    }

    private static Long getCurrentThreadId() {
        return Thread.currentThread().getId();
    }

    private TestSuiteContext() {
        suiteWideInfoMap = new HashMap<String, Object>();
    }

    public void storeInfo(String key, Object value) {
        suiteWideInfoMap.put(key, value);
    }

    public Object retrieveStoredInfo(String key) {
        return suiteWideInfoMap.get(key);
    }

    public Object removeStoredInfo(String key) {
        return suiteWideInfoMap.remove(key);
    }
    private WebDriver driver;

    public void openWebApp(String url) {
        launchBrowser();
        openWebApp(url, 0);
    }

    public void openWebApp(String url, long sleepTime) {
        launchBrowser();
        sleep(sleepTime);
        driver.get(url);
    }

    private void launchBrowser() {
        //driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\Automation\\NewConnect2Yes\\chromedriver\\chromedriver.exe");
        System.out.println("Driver :" + System.getProperty("webdriver.chrome.driver"));
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public WebElement findElement(String elementId) {
        return driver.findElement(By.id(elementId));
    }

    public void clickOnElement(String elementId) {
        findElement(elementId).click();
    }

    public void inputTextOnElement(String elementId) {
    }

    private void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
        }
    }
}
