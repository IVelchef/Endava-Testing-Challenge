# Test Data and Base Setup for API Testing-Reqres.in

## Overview
This project consists of classes and configurations for API testing using REST Assured and TestNG. It includes utilities for generating unique test data, organizing reusable components, and defining a TestNG test suite.

---

## **Classes and Components**

### **1. Test Data Management**
#### **`TestData` Class**
Purpose: Provides a centralized location to store and retrieve test data.

- **Fields:**
  - `randomName`: A dynamically generated unique name for creating users.
  - `userName`: Stores the username of a created user.
  - `firstName`: Stores the first name extracted from user data.
  - `userNamesPage1`, `userNamesPage2`: Lists containing user details from two paginated API responses.
  - `userId`: Stores the ID of a user for reuse.
  - `invalidId`: Predefined ID representing a non-existent user (default: `23`).
  - `createdUniqueUserID`: Stores the ID of a newly created user.

- **Methods:**
  - `getUserNamesPage1()` / `setUserNamesPage1()`: Accessors for user details from page 1.
  - `getUserNamesPage2()` / `setUserNamesPage2()`: Accessors for user details from page 2.
  - Constructor: Automatically assigns a unique random name using the `UniqueNameGenerator` class.

#### **`UniqueNameGenerator` Class**
Purpose: Generates unique names to ensure no duplication in API requests.

- **Fields:**
  - `usedNames`: A `Set` to track already generated names.
  - `faker`: Utilizes the `Faker` library to generate random first names.

- **Methods:**
  - `getInstance()`: Implements the Singleton pattern to ensure a single instance.
  - `generateUniqueName()`: Generates a new name and ensures it is unique by checking against `usedNames`.

---

### **2. Base Setup for API Tests**
#### **`BaseSetup` Class**
Purpose: Contains reusable methods for interacting with the API and performing validations.

- **Fields:**
  - `testData`: Instance of `TestData` for accessing shared data.

- **Methods:**
  - `getListUsersPage1()`: Retrieves user data from page 1 and validates fields like `page`, `per_page`, `total`, etc. Extracts and sorts user data by `first_name`.
  - `getListUsersPage2()`: Similar to page 1 but retrieves data from page 2.
  - `combineAndSortUsers()`: Combines and sorts user data from both pages for validation.
  - `singleUserDetails()`: Fetches and validates details for a single user using `userId`.
  - `invalidUserIdDetails()`: Validates the response for a non-existent user (ID: `23`).
  - `createUser()`: Sends a `POST` request to create a new user, validates the response, and extracts the user ID.
  - `deleteUser()`: Deletes a user by ID, ensures response is empty, and validates headers like `Content-Length` and `X-Powered-By`.
  - `addIssueAndLink()`: Adds Allure links for tracking issues and tasks.

---

## **TestNG Suite Configuration**
#### **`testng.xml` File**
Defines the structure of the TestNG test suite.

- **Suite Name:** `User Tests Suite`
- **Test Name:** `User Tests`
- **Included Classes:**
  - `baseTests.ListUsersTest`
  - `baseTests.SingleUserDetailsTest`
  - `baseTests.NonExistentUserIDTest`
  - `baseTests.CreateUserTest`
  - `baseTests.DeleteUserTest`

---

## **Workflow Summary**
1. **Data Initialization:**
   - `TestData` initializes reusable fields and generates unique names using `UniqueNameGenerator`.

2. **API Testing:**
   - `BaseSetup` provides methods to perform CRUD operations and validations on the API endpoints.

3. **Validation:**
   - Validates response structure, headers, and formats using REST Assured and Hamcrest matchers.

4. **Test Execution:**
   - The `testng.xml` file organizes the test cases and ensures sequential execution.

---

## **Key Libraries Used**
- **REST Assured:** For API interaction and validation.
- **TestNG:** For test case organization and execution.
- **Hamcrest:** For readable and flexible assertions.
- **Faker:** For generating random test data.
- **Allure:** For linking tasks and issues in test reports.

---

## **Highlights**
- Efficient data reuse with `TestData` and Singleton pattern in `UniqueNameGenerator`.
- Comprehensive validation of API responses, including headers, body, and formats.
- Modular and maintainable test structure using TestNG and REST Assured.
- Extensibility for additional tests by following existing patterns.
