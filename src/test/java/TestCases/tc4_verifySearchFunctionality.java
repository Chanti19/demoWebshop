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

public class tc4_verifySearchFunctionality {

    WebDriver driver;
    homePage hp;
    ExtentReports extent;
    ExtentTest test;

    private static final String BASE_URL        = "https://demowebshop.tricentis.com/";
    private static final String SEARCH_KEYWORD  = "book";
    private static final String EXPECTED_URL_FRAGMENT = "/search?q=" + SEARCH_KEYWORD;

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
     * TC4: Verify that searching for a keyword redirects to the search results page.
     */
    @Test(priority = 4, description = "Verify Search Functionality")
    public void verifySearchFunctionality() {
        test = extent.createTest("TC4 - Verify Search Functionality",
                "Verify that searching for '" + SEARCH_KEYWORD
                        + "' navigates to the search results page");
        try {
            hp.searchForProduct(SEARCH_KEYWORD);

            String currentUrl = hp.getCurrentUrl();
            test.log(Status.INFO, "Current URL after search: " + currentUrl);

            Assert.assertTrue(currentUrl.contains(SEARCH_KEYWORD),
                    "Search results URL should contain keyword '" + SEARCH_KEYWORD
                            + "'. Actual URL: " + currentUrl);

            // Also verify page title changes to include "Search"
            String pageTitle = driver.getTitle();
            test.log(Status.INFO, "Page title after search: " + pageTitle);
            Assert.assertTrue(pageTitle.toLowerCase().contains("search"),
                    "Page title should contain 'search' after performing a search");

            test.log(Status.PASS, "Search functionality verified. Redirected to: " + currentUrl);
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Search functionality failed: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
        extent.flush();
    }
}
