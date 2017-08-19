package com.github.slamdev.selenium;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.bridge.SLF4JBridgeHandler;

public class SampleTest {

    private static final boolean CLOSE_BROWSER_AFTER_TEST = false;

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
        ChromeDriverManager.getInstance().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null && CLOSE_BROWSER_AFTER_TEST) {
            driver.quit();
        }
    }

    @Test
    public void css_locators() {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        driver.findElement(By.cssSelector("input[type='search']")).sendKeys("Software");
        driver.findElement(By.cssSelector("input[id='searchButton']")).click();
        Assert.assertEquals("Software", driver.findElement(By.cssSelector("h1[id='firstHeading']")).getText());
    }
}
