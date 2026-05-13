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

public class tc5_verifyRegisterNavigation {

    WebDriver driver;
    homePage hp;
    ExtentReports extent;
    ExtentTest test;

    private static final String BASE_URL           = "https://demowebshop.tricentis.com/";
    private static final String EXPECTED_REGISTER_URL = "https://demowebshop.tricentis.com/register";
    private static final String EXPECTED_REGISTER_TITLE = "Demo Web Shop. Register";

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
     * TC5: Verify clicking "Register" navigates to the registration page
     *      and the page title/URL are correct.
     */
    @Test(priority = 5, description = "Verify Register Page Navigation")
    public void verifyRegisterPageNavigation() {
        test = extent.createTest("TC5 - Verify Register Page Navigation",
                "Verify that clicking the Register link opens the registration page "
                        + "with the correct URL and title");
        try {
            hp.clickRegister();

            String currentUrl = driver.getCurrentUrl();
            String pageTitle  = driver.getTitle();

            test.log(Status.INFO, "Current URL after clicking Register: " + currentUrl);
            test.log(Status.INFO, "Page title after clicking Register: " + pageTitle);

            // Verify URL
            Assert.assertEquals(currentUrl, EXPECTED_REGISTER_URL,
                    "Register URL mismatch! Expected: " + EXPECTED_REGISTER_URL
                            + " | Actual: " + currentUrl);
            test.log(Status.PASS, "Register URL verified: " + currentUrl);

            // Verify page title
            Assert.assertEquals(pageTitle, EXPECTED_REGISTER_TITLE,
                    "Register page title mismatch!");
            test.log(Status.PASS, "Register page title verified: " + pageTitle);

        } catch (AssertionError e) {
            test.log(Status.FAIL, "Register navigation failed: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
        extent.flush();
    }
}
