package TestCases;

import PageObjects.homePage;
import Utilities.reportGenerator;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class tc1_verifyHomePage {

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
     * TC1: Verify the home page title is correct.
     * Expected Title: "Demo Web Shop"
     */
    @Test(priority = 1, description = "Verify Home Page Title")
    public void verifyHomePageTitle() {
        test = extent.createTest("TC1 - Verify Home Page Title",
                "Verify that the home page title is 'Demo Web Shop'");
        try {
            hp.checkTitle("Demo Web Shop");
            test.log(Status.PASS, "Home page title verified successfully: 'Demo Web Shop'");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Title mismatch: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
        extent.flush();
    }
}
