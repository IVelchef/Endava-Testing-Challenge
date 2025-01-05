package saucedemotests.core;
import com.saucedemo.pages.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import testframework.PropertiesManager;
import testframework.core.BaseWebTest;

public class SauceDemoBaseWebTest extends BaseWebTest {
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected ShoppingCartPage shoppingCartPage;
    protected CheckoutOverviewPage checkoutOverviewPage;
    protected CheckoutYourInformationPage checkoutYourInformationPage;
    protected CheckoutCompletePage checkoutCompletePage;

    @BeforeEach
    public void beforeTests() {

        inventoryPage = new InventoryPage();
        loginPage = new LoginPage();
        shoppingCartPage = new ShoppingCartPage();
        checkoutOverviewPage = new CheckoutOverviewPage();
        checkoutYourInformationPage = new CheckoutYourInformationPage();
        checkoutCompletePage = new CheckoutCompletePage();

        driver().get(PropertiesManager.getConfigProperties().getProperty("sauceDemoBaseUrl"));
    }



    @AfterAll
    public static void afterAll() {

        driver().close();
    }

    public void authenticateWithUser(String username, String pass) {
        loginPage.submitLoginForm(username, pass);
        inventoryPage.waitForPageTitle();
    }
}