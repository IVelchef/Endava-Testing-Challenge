package com.saucedemo.pages;

import org.openqa.selenium.By;

public class CheckoutCompletePage extends BaseSauceDemoPage {

    public CheckoutCompletePage() {
        super("/checkout-complete.html");
    }
    private final By confirmationMessageLocator = By.className("complete-header");

    public String getConfirmationMessage() {
        return driver().findElement(confirmationMessageLocator).getText();
    }
}
