# SauceDemo Automated Test Suite

## Project Overview

This project involves creating automated test cases for SauceDemo using Selenium WebDriver and JUnit 5. The goal of these tests is to validate that core functionalities of the application work as expected, including logging in, interacting with the shopping cart, completing a purchase, and sorting items by price.

The test scenarios covered are:
- Logging in and interacting with the shopping cart.
- Sorting items by price in descending order.
- Completing the checkout process and verifying that the order was placed correctly.
- Verifying that the cart is emptied after the checkout process.

## Technologies Used

- **Selenium WebDriver**: For automating browser interactions and simulating user actions on the web.
- **JUnit 5**: For organizing, structuring, and executing the tests.
- **Java**: The programming language used to write the tests.
- **Maven**: For managing dependencies and automating the build and test process.
- **Page Object Model**: An architectural pattern that helps organize and maintain the code by creating page classes for different sections of the application.

## Key Features Implemented

- **Dynamic Credential Management**: Credentials are not hardcoded in the test code. They can be loaded from external configuration files or environment variables, ensuring better maintainability and security.
- **Assertions**: At each step of the test, assertions are used to ensure that the expected behavior is achieved. For example:
    - Verifying the correct items are added to the cart.
    - Confirming that the cart is empty after checkout.
    - Ensuring that items are sorted correctly by price.
- **Page Object Model (POM)**: The application is divided into different page classes for better organization. Each page class contains methods for interacting with web elements that are specific to that page (e.g., adding/removing items from the cart, selecting sorting options).
- **Explicit Waits**: To deal with dynamic content loading, explicit waits are implemented to ensure that elements are interactable before interacting with them, preventing issues with timing and synchronization.

## Key Classes in the Project

- **BaseSauceDemoPage**  
  This is the base class for all pages. It handles URL navigation and common methods that are shared across different pages of the application.

- **LoginPage**  
  Manages the login process, including handling dynamic usernames and passwords. It ensures that the login functionality works smoothly for different users.

- **InventoryPage**  
  Manages the products listed on the inventory page. This includes methods for adding and removing products from the shopping cart.

- **ShoppingCartPage**  
  Handles interactions with the shopping cart. This includes verifying that products are correctly added or removed and ensuring the total price is calculated correctly.

- **Checkout Pages**  
  Includes classes for each step of the checkout process:
    - **CheckoutYourInformationPage**: Manages the user’s personal details during the checkout.
    - **CheckoutOverviewPage**: Displays the overview of the products and total amount before confirming the order.
    - **CheckoutCompletePage**: Confirms that the order was placed successfully.

## Test Data Management

- **TestData Enum**  
  This Enum stores key test data, such as the username and password. It ensures that the test data is centralized and easy to manage, making it easier to reuse across different tests.

## Test Classes

- **LoginTests**  
  This class tests the login functionality with dynamic user credentials, ensuring that the system works correctly for each provided user.

- **ProductsTests**  
  This class tests the functionalities related to the products, including:
    - Adding items to the cart.
    - Removing products from the cart.
    - Adding new products to the cart.
    - Verifying the total price calculation.
    - Sorting the products by price.

# Running the Project and Generating Allure Reports

Follow the instructions below to run the project and generate an Allure report on your system. These steps are suitable for both Mac and Windows users.

---

## Environment Setup
Ensure the following tools are installed on your system:
- **Java JDK** (check with `java -version`)
- **Maven** (check with `mvn -version`)
- **Allure** (check with `allure --version`)

Clone or download the project into a local directory.

---

## Steps for Mac and Windows

### **Mac**
1. Open **Terminal**.
2. Navigate to the project directory by replacing `/path/to/your/project/directory` with the actual path where the project is located. For example:
   ```bash
   cd "/Users/yourusername/Downloads/your-project-folder"
   rm -rf target/allure-results
   mvn test
   allure serve target/allure-results

### **Windows**
1. **Open Command Prompt (CMD) or PowerShell.**

2. **Navigate to the project directory**  
   Replace `C:\path\to\your\project\directory` with the actual path where the project is located. For example:
   ```powershell
   cd "C:\Users\yourusername\Downloads\your-project-folder"
   rmdir /s /q target\allure-results
   mvn test
   allure serve target\allure-results


## Browsers Tested: Chrome and Firefox

The tests have been successfully executed on the following browsers:
- **Google Chrome Version 131.0.6778.205 (Official Build) (arm64)**
- **Firefox Version 133.0.3**

Both browsers have been tested to ensure compatibility and performance


