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
