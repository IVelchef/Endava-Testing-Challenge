# Endava Testing Challenge 

## Overview

This repository contains solutions for the **Endava Testing Challenge** tasks. The projects in this repository cover testing of **REST API** using Postman and REST Assured, as well as **Web UI** automation using Selenium WebDriver. Each project has a corresponding `.md` file that explains the approach and logic for completing the task.

## Repository Structure

### 1. **Reqres POSTMAN_Endava_Challenge/**
   This folder contains API tests in Postman, based on the public REST API [Reqres](https://reqres.in/). The tests cover the following scenarios:
   - Listing available users
   - Retrieving details of a specific user
   - Test for retrieving a non-existing user
   - Creating a new user
   - Deleting a newly created user
   - Parameterizing the base URL for the API
   Each scenario is documented in the respective `.md` files.

### 2. **Reqres-REST_Assured_Endava_Challenge/**
   This folder contains API tests implemented with **REST Assured**, focusing on the same public REST API for users. The approach is similar to the Postman tests but uses Java and REST Assured to automate the tests. Here you will find:
   - The same API tests, but in Java
   - Full examples of JSON response validation and error handling
   - Parameterized test cases for easy setup of the test environment

### 3. **Saucedemo Tests-Selenium-WebDriver-Framework-Endava-Challenge/**
   This folder contains automated Web UI tests based on **Selenium WebDriver**. The tests are for the public Saucedemo website, focusing on the following tasks:
   - Logging in and adding products to the cart
   - Verifying the correctness of added/removed items
   - Completing an order and confirming the successful process
   - Logging in with a user, sorting products by price, and logging out of the system
   Each test scenario is thoroughly explained in the `.md` file within the respective folder.

## Documentation

Each task in the repository contains a corresponding `.md` file with detailed explanations of the logic and approach for solving the tasks.

These files are the primary source of information regarding the methods and steps to complete the tasks.

## Requirements

### **REST API Task**

#### **Scenario**

1. List available users**
2. Get extracted user details
3. Try to get details of a user that doesn't exist
4. Create a UNIQUE new user
5. Delete newly created user
  
---

### **Web UI Task**

**Scenario 1**
  
1. Log in with the standard user.
2. Add the **first** and **last** item in the cart, verify that the correct items are added.
3. Remove the first item and add the previous one to the last item in the cart, verify the content again.
4. Go to checkout.
5. Finish the order.
6. Verify that the order is placed.
7. Verify that the cart is empty.
8. Log out from the system.

---

**Scenario 2**

1. Log in with the standard user.
2. Verify that when sorting is selected "Price (high to low)", the items are sorted in the correct order.
3. Log out from the system.

---

## Conclusion

This repository provides automated tests and documented solutions for two main aspects of testing: REST API and Web UI. Each project is separated into distinct folders with clearly defined test frameworks and documentation of approaches, making it easier to expand and maintain the tests.
