package TestCases;

import PageObjects.homePage;
import Utilities.reportGenerator;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class tc2_verifyLogo {

    WebDriver driver;
    homePage hp;
    ExtentReports extent;
    ExtentTest test;

    private static final String BASE_URL = "https://demowebshop.tricentis.com/";

    @BeforeClass
    public void setUp() {
        extent = reportGenerator.getInstance();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        hp = new homePage(driver);
        hp.navigateTo(BASE_URL);
    }

    /**
     * TC2: Verify the store logo is visible on the home page.
     */
    @Test(priority = 2, description = "Verify Store Logo is Displayed")
    public void verifyLogoDisplayed() {
        test = extent.createTest("TC2 - Verify Store Logo is Displayed",
                "Verify that the Tricentis Demo Web Shop logo is visible on the home page");
        try {
            boolean isDisplayed = hp.isLogoDisplayed();
            Assert.assertTrue(isDisplayed, "Logo should be visible on the home page");
            test.log(Status.PASS, "Store logo is displayed successfully on the home page");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Logo not displayed: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
        extent.flush();
    }
}
