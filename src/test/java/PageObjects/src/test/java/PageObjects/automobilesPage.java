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

public class automobilesPage {

    WebDriver driver;
    WebDriverWait wait;

    // ---- Locators ----
    @FindBy(css = ".category-title h1")
    WebElement pageHeading;

    @FindBy(css = ".product-item")
    List<WebElement> productList;

    @FindBy(css = ".product-item .product-title a")
    List<WebElement> productTitles;

    @FindBy(css = ".product-item .prices .price.actual-price")
    List<WebElement> productPrices;

    @FindBy(css = ".pager .current-page")
    WebElement currentPage;

    // ---- Constructor ----
    public automobilesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // ---- Actions & Assertions ----

    public String getPageHeading() {
        wait.until(ExpectedConditions.visibilityOf(pageHeading));
        return pageHeading.getText();
    }

    public int getProductCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productList));
        return productList.size();
    }

    public List<WebElement> getAllProductTitles() {
        return productTitles;
    }

    public List<WebElement> getAllProductPrices() {
        return productPrices;
    }

    public void navigateTo(String url) {
        driver.get(url);
    }
}
