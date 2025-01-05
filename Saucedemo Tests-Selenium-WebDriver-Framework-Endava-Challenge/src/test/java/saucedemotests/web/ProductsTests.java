package saucedemotests.web;
import com.saucedemo.pages.InventoryPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saucedemotests.core.SauceDemoBaseWebTest;
import saucedemotests.enums.TestData;
import java.util.List;



public class ProductsTests extends SauceDemoBaseWebTest {


    @BeforeEach
    public void beforeTest(){

        authenticateWithUser( "standard_user" , TestData.PASSWORD.getValue());
        inventoryPage.resetShoppingCart();
    }


    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void AddProductsByIndexInCart() {

        inventoryPage.addProductsToCart();
        shoppingCartPage.navigate();

        List<String> addedProductNames = inventoryPage.getAddedProductNames();
        List<String> cartProductNames = shoppingCartPage.getCartProductNames();

        Assertions.assertTrue(cartProductNames.containsAll(addedProductNames),
                "The cart does not contain the expected products.");

        int itemCount = shoppingCartPage.getCartItemCount();
        Assertions.assertEquals(2, itemCount, "Shopping cart badge count should be 2.");


        inventoryPage.removeFirstAndAddSecondLastProduct();
        shoppingCartPage.navigate();

        List<String> updatedAddedProductNames = inventoryPage.getAddedProductNames();
        List<String> updatedCartProductNames = shoppingCartPage.getCartProductNames();

        Assertions.assertTrue(updatedCartProductNames.containsAll(updatedAddedProductNames),
                "The updated cart does not contain the expected products.");
        Assertions.assertEquals(updatedAddedProductNames, updatedCartProductNames,
                "The products in the cart do not match the expected products.");

        checkoutYourInformationPage.navigate();
        checkoutYourInformationPage.fillShippingDetails("Billy", "Gibbons", "1000");
        checkoutYourInformationPage.clickContinue();

        double expectedTotal = checkoutOverviewPage.calculateExpectedTotal(shoppingCartPage);
        double actualTotal = checkoutOverviewPage.getTotalAmount();
        Assertions.assertEquals(expectedTotal, actualTotal, "Items total price not as expected");


        checkoutOverviewPage.clickFinish();
        var confirmationMessage = checkoutCompletePage.getConfirmationMessage();
        Assertions.assertTrue(confirmationMessage.contains("Thank you for your order!"),
                "Order was not completed successfully");

        inventoryPage.clickShoppingCartLink();
        var cartItemsAfterOrder = shoppingCartPage.getShoppingCartItems();
        Assertions.assertEquals(0, cartItemsAfterOrder.size(),
                "Shopping cart is not empty after order completion");

        inventoryPage.logout();
    }



    @Test
    public void VerifySortingByPriceHighToLow() {

        inventoryPage.navigate();
        inventoryPage.selectSortOptionByPriceHighToLow();

        List<Double> productPrices = inventoryPage.getProductPrices();

        boolean isSorted = InventoryPage.isSortedDescending(productPrices);
        Assertions.assertTrue(isSorted, "The products are not sorted from high to low price.");

        inventoryPage.logout();
    }
}