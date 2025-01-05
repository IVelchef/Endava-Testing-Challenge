package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends BaseSauceDemoPage {
    public ShoppingCartPage() {
        super("/cart.html");
    }

    private final By inventoryItems = By.className("inventory_item_name");
    private final By cartItemsLocator = By.className("cart_item");
    private final By cartBadgeLocator = By.cssSelector("span.shopping_cart_badge[data-test='shopping-cart-badge']");
    private final By priceTextLocator = By.className("inventory_item_price");

    public List<WebElement> getShoppingCartItems() { return driver().findElements(inventoryItems); }


    public List<String> getCartProductNames() {
        List<WebElement> cartItems = driver().findElements(cartItemsLocator);
        List<String> cartProductNames = new ArrayList<>();

        for (WebElement cartItem : cartItems) {
            String productName = cartItem.findElement(inventoryItems).getText();
            cartProductNames.add(productName);
        }

        return cartProductNames;
    }

    public int getCartItemCount() {
        WebElement cartBadge = driver().findElement(cartBadgeLocator);
        return Integer.parseInt(cartBadge.getText());
    }

    public List<Double> getCartProductPrices() {
        List<WebElement> cartItems = driver().findElements(cartItemsLocator);
        List<Double> productPrices = new ArrayList<>();
        for (WebElement cartItem : cartItems) {
            String priceText = cartItem.findElement(priceTextLocator).getText().replace("$", "");
            productPrices.add(Double.parseDouble(priceText));
        }
        return productPrices;
    }
}