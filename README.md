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

## Conclusion

This repository provides automated tests and documented solutions for two main aspects of testing: REST API and Web UI. Each project is separated into distinct folders with clearly defined test frameworks and documentation of approaches, making it easier to expand and maintain the tests.
