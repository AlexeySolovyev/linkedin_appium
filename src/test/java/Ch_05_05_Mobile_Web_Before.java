import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ch_05_05_Mobile_Web_Before {

    private static final String APPIUM = "http://localhost:4723/wd/hub";

    private RemoteWebDriver driver; // Selenium web driver API

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
        driver.get("http://appiumpro.com");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toggleMenu"))).click();
        driver.findElement(By.cssSelector("a[href='/editions']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(), 'All Editions')]")));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
