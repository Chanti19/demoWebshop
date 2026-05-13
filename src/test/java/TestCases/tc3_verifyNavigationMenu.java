package TestCases;

import PageObjects.homePage;
import Utilities.reportGenerator;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class tc3_verifyNavigationMenu {

    WebDriver driver;
    homePage hp;
    ExtentReports extent;
    ExtentTest test;

    private static final String BASE_URL = "https://demowebshop.tricentis.com/";

    // Expected top-level nav items on demowebshop.tricentis.com
    private static final List<String> EXPECTED_MENU_ITEMS = Arrays.asList(
            "Books", "Computers", "Electronics", "Apparel & Shoes",
            "Digital downloads", "Jewelry", "Gift Cards"
    );

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
     * TC3: Verify that all expected navigation menu items are present.
     */
    @Test(priority = 3, description = "Verify Navigation Menu Items")
    public void verifyNavigationMenuItems() {
        test = extent.createTest("TC3 - Verify Navigation Menu Items",
                "Verify that all top-level navigation menu items are displayed on the home page");
        try {
            List<WebElement> menuItems = hp.getNavMenuItems();
            List<String> actualItems = menuItems.stream()
                    .map(WebElement::getText)
                    .map(String::trim)
                    .collect(Collectors.toList());

            test.log(Status.INFO, "Actual menu items found: " + actualItems);
            test.log(Status.INFO, "Expected menu items: " + EXPECTED_MENU_ITEMS);

            for (String expected : EXPECTED_MENU_ITEMS) {
                Assert.assertTrue(actualItems.contains(expected),
                        "Menu item missing: " + expected);
                test.log(Status.PASS, "Menu item present: '" + expected + "'");
            }

            test.log(Status.PASS, "All " + EXPECTED_MENU_ITEMS.size()
                    + " navigation menu items verified successfully");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Navigation menu verification failed: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
        extent.flush();
    }
}
