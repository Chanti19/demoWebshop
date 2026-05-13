package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class homePage {

    WebDriver driver;
    WebDriverWait wait;

    // ---- Locators ----
    @FindBy(css = ".header-logo img")
    WebElement logo;

    @FindBy(css = ".store-search-box input[name='q']")
    WebElement searchBox;

    @FindBy(css = ".store-search-box button[type='submit']")
    WebElement searchButton;

    @FindBy(css = ".top-menu > li > a")
    List<WebElement> navMenuItems;

    @FindBy(css = ".register-button > a")
    WebElement registerLink;

    @FindBy(css = ".login-button > a")
    WebElement loginLink;

    @FindBy(css = ".home-page-polls")
    WebElement pollSection;

    @FindBy(css = ".product-grid .product-item")
    List<WebElement> featuredProducts;

    @FindBy(css = "#small-searchterms")
    WebElement searchField;

    // ---- Constructor ----
    public homePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // ---- Actions & Assertions ----

    /**
     * TC1 – Verify the page title matches the expected title.
     */
    public void checkTitle(String expTitle) {
        String actTitle = driver.getTitle();
        assertEquals(actTitle, expTitle,
                "Title mismatch! Expected: " + expTitle + " | Actual: " + actTitle);
    }

    /**
     * TC2 – Verify the logo is displayed on the home page.
     */
    public boolean isLogoDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(logo));
        return logo.isDisplayed();
    }

    /**
     * TC3 – Verify navigation menu items are present and return their labels.
     */
    public List<WebElement> getNavMenuItems() {
        wait.until(ExpectedConditions.visibilityOfAllElements(navMenuItems));
        return navMenuItems;
    }

    /**
     * TC4 – Enter a keyword in the search box and submit search.
     */
    public void searchForProduct(String keyword) {
        wait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.clear();
        searchField.sendKeys(keyword);
        searchButton.click();
    }

    /**
     * TC5 – Click on the Register link and return the resulting page URL.
     */
    public void clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink));
        registerLink.click();
    }

    /**
     * Utility – Navigate to the home page URL.
     */
    public void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * Utility – Get current page URL.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
