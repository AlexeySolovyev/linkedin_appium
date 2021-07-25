import io.appium.java_client.MobileBy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class Ch_05_06_Challenge_web_form {

    private static final String APPIUM = "http://localhost:4723/wd/hub";
    private static final String SITE = "http://appiumpro.com";
    private RemoteWebDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","iOS");
        caps.setCapability("platformVersion", "14.5");
        caps.setCapability("deviceName", "iPhone 8");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("browserName", "Safari");
        driver = new RemoteWebDriver(new URL(APPIUM), caps);
    }

    @Test
    public void test() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get(SITE);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toggleMenu"))).click();
        driver.findElement(By.cssSelector("a[href='/contact']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("contactEmail"))).sendKeys("test@gmail.org");
        driver.findElement(By.id("contactText")).sendKeys("Here some test text");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='response___1yZzw error___2pSWM']")));
        WebElement errorText = driver.findElement(By.cssSelector("div[class='response___1yZzw error___2pSWM']"));
        assert(errorText.getText().contains("Captcha"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
