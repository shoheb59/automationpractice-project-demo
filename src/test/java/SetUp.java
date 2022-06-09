import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.time.Duration;

public class SetUp {

    WebDriver driver;

    @BeforeTest
    public void setup()
    {
        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headed");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterTest
    public void closeDriver()
    {
        //driver.close();
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result)
    {
        if (ITestResult.FAILURE == result.getStatus())
        try
        {
            Utils util = new Utils(driver);
            util.takeScreenShot();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
