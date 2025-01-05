package com.saucedemo.pages;

import org.openqa.selenium.By;

import java.util.List;


public class CheckoutOverviewPage extends BaseSauceDemoPage {
    public CheckoutOverviewPage() {
        super("/checkout-step-two.html");
    }

    private final By taxTextLocator = By.className("summary_tax_label");
    private final By summaryTotalLabel = By.className("summary_total_label");
    private final By cartButton = By.xpath("//*[contains(@class,'cart_button')]");


    public void clickFinish() {
        driver().findElement(cartButton).click();
    }

    public double getTaxAmount() {
        String taxText = driver().findElement(taxTextLocator).getText().replace("Tax: $", "");
        return Double.parseDouble(taxText);
    }

    public double getTotalAmount() {
        String totalText = driver().findElement(summaryTotalLabel).getText().replace("Total: $", "");
        return Double.parseDouble(totalText);
    }

    public double calculateExpectedTotal(ShoppingCartPage shoppingCartPage) {
        List<Double> productPrices = shoppingCartPage.getCartProductPrices();


        double subtotal = productPrices.stream().mapToDouble(Double::doubleValue).sum();

        double tax = getTaxAmount();

        return subtotal + tax;
    }
}
