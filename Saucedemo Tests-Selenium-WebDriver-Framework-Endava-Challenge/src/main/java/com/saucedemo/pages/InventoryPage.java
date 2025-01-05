package com.saucedemo.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BaseSauceDemoPage {


    public InventoryPage() {
        super("/inventory.html");
    }

    private final By shoppingCartLink = By.className("shopping_cart_link");
    private final By pageTitle = By.xpath("//span[@class='title' and text()='Products']");
    private final By burgerMenu = By.xpath("//div[@class='bm-burger-button']");
    private final By logoutButton = By.xpath("//a[@data-test='logout-sidebar-link']");
    private final By burgerButton = By.id("react-burger-menu-btn");
    private final By resetSidebarLink = By.id("reset_sidebar_link");
    private final By getAllProducts = By.xpath("//div[@data-test='inventory-item']");
    private final By lastProductLocator = By.className("btn_inventory");
    private final By firstProductLocator = By.className("btn_inventory");
    private final By firstProductNameLocator = By.className("inventory_item_name");
    private final By lastProductNameLocator = By.className("inventory_item_name");
    private final By containerLocator =By.id("cart_contents_container");
    private final By firstRemoveButtonLocator = By.cssSelector("button.btn.btn_secondary.btn_small.cart_button");
    private final By firstProductNameElementLocator = By.className("inventory_item_name");
    private final By continueShoppingButtonLocator = By.id("continue-shopping");
    private final By secondLastProductNameLocator = By.className("inventory_item_name");
    private final By sortDropdownLocator = By.className("product_sort_container");
    private final By productPricesLocator = By.className("inventory_item_price");

    public static final String SELECTED_SORTED = "hilo";


    protected List<WebElement> getAllProducts(){
        return driver().findElements(getAllProducts);
    }


    private final List<String> addedProductNames = new ArrayList<>();

    public void addProductsToCart() {
        List<WebElement> products = getAllProducts();

        if (products.size() >= 2) {
            WebElement firstProduct = products.get(0);
            String firstProductName = firstProduct.findElement(firstProductNameLocator).getText();
            addedProductNames.add(firstProductName);
            firstProduct.findElement(firstProductLocator).click();

            WebElement lastProduct = products.get(products.size() - 1);
            String lastProductName = lastProduct.findElement(lastProductNameLocator).getText();
            addedProductNames.add(lastProductName);
            lastProduct.findElement(lastProductLocator).click();

            System.out.println("Added products to cart: " + addedProductNames);
        } else {
            Assertions.fail("Not enough products available to add the first and last items.");
        }
    }

    public List<String> getAddedProductNames() {
        return addedProductNames;
    }

    public void removeFirstAndAddSecondLastProduct() {
        WebElement container = driver().findElement(containerLocator);

        WebElement firstRemoveButton = container.findElement(firstRemoveButtonLocator);

        WebElement firstProductNameElement = container.findElement(firstProductNameElementLocator);
        String firstProductName = firstProductNameElement.getText();
        addedProductNames.remove(firstProductName);
        firstRemoveButton.click();

        WebElement continueShoppingButton = driver().findElement(continueShoppingButtonLocator);
        continueShoppingButton.click();

        List<WebElement> products = getAllProducts();

        if (products.size() >= 2) {
            WebElement secondLastProduct = products.get(products.size() - 2);
            String secondLastProductName = secondLastProduct.findElement(secondLastProductNameLocator).getText();

            secondLastProduct.findElement(lastProductLocator).click();

            addedProductNames.add(secondLastProductName);

            System.out.println("Updated products in cart: " + addedProductNames);
        } else {
            Assertions.fail("Not enough products available to add the second-last item");
        }
    }


    public void clickShoppingCartLink() {
        driver().findElement(shoppingCartLink).click();
    }

    public void waitForPageTitle() {
        var title = driver().findElement(pageTitle);
        driverWait().until(ExpectedConditions.visibilityOf(title));
    }

    public void logout() {
        driver().findElement(burgerMenu).click();
        var logoutBtn = driver().findElement(logoutButton);
        driverWait().until(ExpectedConditions.elementToBeClickable(logoutBtn));
        logoutBtn.click();
    }

    public void resetShoppingCart() {
        driver().findElement(burgerButton).click();
        driver().findElement(resetSidebarLink).click();
        driver().navigate().refresh();
    }

    public boolean isLogoutButtonVisible() {
        driver().findElement(burgerMenu).click();
        var logoutBtn = driver().findElement(logoutButton);
        return logoutBtn.isDisplayed();
    }

    public void selectSortOptionByPriceHighToLow() {
        WebElement sortDropdown = driver().findElement(sortDropdownLocator);

        Select select = new Select(sortDropdown);

        select.selectByValue(SELECTED_SORTED);
    }

    public List<Double> getProductPrices() {
        List<WebElement> productElements = driver().findElements(productPricesLocator);
        List<Double> productPrices = new ArrayList<>();

        for (WebElement productElement : productElements) {
            String priceText = productElement.getText().replace("$", "").trim();
            productPrices.add(Double.parseDouble(priceText));
        }

        return productPrices;
    }


        public static boolean isSortedDescending(List<Double> prices) {
            for (int i = 0; i < prices.size() - 1; i++) {
                if (prices.get(i) < prices.get(i + 1)) {
                    return false;
                }
            }
            return true;
        }
    }

