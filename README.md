# Endava Testing Challenge

## Web UI Task description
1) Inside "Web_UI_Task.md" file.
2) You can select any programming language that Selenium WebDriver/Playwright supports.

## REST API Task description
1) Inside "REST_API_Task.md" file.

## Timebox 
The tasks should be completed within one week. 



# REST API Task

## Description

Use public REST API https://reqres.in/ to complete scenario steps.

## Scenario
1. List available users
	- GET */api/users?page=1*
	- Execute one or many JSON Response Assertions
	- Extract single user details (Id, Email)
	- (Optional) Extract all users, sort them by First Name alphabetically. Print sorted collection.
2. Get extracted user details
	- GET */api/users/{USER_ID}*
	- Execute one or many JSON Response Assertions
3. Try to get details of user that doesn't exist
	- GET */api/users/{USER_ID}*
	- Execute one or many Assertions
4. Create UNIQUE new user
	- POST */api/users*
	- Execute one or many JSON Response Assertions
5. Delete newly created user
	- DELETE */api/users/{USER_ID}*
	- Execute one or many Assertions
6. Parameterize base URL




# Web UI Task

## Description

Use the [following](https://www.saucedemo.com/) public website for your automation task.

The following tools/libraries can be used:
* [Selenium](https://www.selenium.dev/)
* [Playwright](https://playwright.dev)
* Language of your choice that supports Selenium/Playwright

Organize your solution in a zip archive and send it back. 
Note! Please remove all the compiled/built data, if any. 

## Version 1
#### Scenario 1
Use the standard user and password (they are prone to change, think how to obtain them)
- Log in with the standard user
- Add the first and the last item in the cart, verify the correct items are added
- Remove the first item and add previous to the last item to the cart, verify the content again
- Go to checkout
- Finish the order
- Verify order is placed
- Verify cart is empty
- Logout from the system

#### Scenario 2

- Log in with the standard user
- Verify when for sorting it is selected "Price (high to low)"
- Then the items are sorted in the correct manner
- Logout from the system

## Version 2
Implement the tasks written in **Version 1** and do the following as well
- Add an ability to filter tests for the test execution 
- Add custom HTML report for the test execution
- Tests will be executed on multiple environments (dev, testing, staging, etc..), add necessary configurations.
- Chrome and Firefox should be supported browsers

## As a bonus, per your choice
- Support different browser resolutions

