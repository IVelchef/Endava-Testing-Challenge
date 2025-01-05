package com.saucedemo.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LoginPage extends BaseSauceDemoPage {


    public LoginPage() {
        super("/");
    }

    private final By usernameLocator = By.xpath("//input[@data-test='username']");
    private final By passwordLocator = By.xpath("//input[@data-test='password']");
    private final By loginButtonLocator = By.xpath("//input[@id='login-button']");
    private final By credentialsContainerLocator = By.id("login_credentials");
    public static final String ACCEPTED_USERNAMES_ARE = "Accepted usernames are:";


    public void submitLoginForm(String username, String pass) {
        WebElement usernameInput = driver().findElement(usernameLocator);
        usernameInput.sendKeys(username);

        WebElement password = driver().findElement(passwordLocator);
        password.sendKeys(pass);

        WebElement loginButton = driver().findElement(loginButtonLocator);
        loginButton.click();
    }

    public List<String> getAvailableUsernames() {
        WebElement usernameContainer = driver().findElement(credentialsContainerLocator);

        String rawText = usernameContainer.getText();

        List<String> usernames = Arrays.stream(rawText.split("\n"))
                .filter(line -> line.trim().length() > 0 && !line.startsWith(ACCEPTED_USERNAMES_ARE))
                .collect(Collectors.toList());

        return usernames;
    }
}