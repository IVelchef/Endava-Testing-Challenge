package saucedemotests.web;
import com.saucedemo.pages.LoginPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import saucedemotests.core.SauceDemoBaseWebTest;
import saucedemotests.enums.TestData;

import java.util.List;

public class LoginTests extends SauceDemoBaseWebTest {


    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testLoginWithDynamicUsernames() {
        LoginPage loginPage = new LoginPage();
        loginPage.navigate();

        List<String> usernames = loginPage.getAvailableUsernames();

        if (usernames.isEmpty()) {
            System.out.println("No usernames found.");
            return;
        }

        for (String username : usernames) {
            try {
                loginPage.navigate();
                loginPage.submitLoginForm(username, TestData.PASSWORD.getValue());

                inventoryPage.waitForPageTitle();
                inventoryPage.assertNavigated();

                Assertions.assertTrue(inventoryPage.isLogoutButtonVisible(),
                        "Logout button is not visible! Login failed for user: " + username);

                System.out.println("Login successful for user: " + username);
                inventoryPage.logout();

            } catch (Exception e) {
                System.out.println("Login failed for user: " + username);
            }
        }
    }
}
