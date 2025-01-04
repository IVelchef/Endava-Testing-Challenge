# API Test Collection - Postman

This project includes a test suite in Postman to validate API responses. The main goal is to ensure that the API returns correct data with the proper status codes and response formats, and that important properties like `id`, `email`, `first_name`, `last_name`, and `avatar` are present and formatted correctly. Below is an explanation of what was done step by step.

## 1. **Status Code Tests**

First, I focused on validating the status codes returned by the API:

- **200 (OK)**: I checked that the API returns a status code of 200, which indicates that the request was successful.
- **404 (Not Found)**: For invalid or non-existing resources, I tested whether the API returns a 404 status code.
- **201 (Created)**: When creating a new user, I validated that the API returns a 201 status code.
- **204 (No Content)**: For requests that shouldn't return any content (like deleting data), I tested that the status code was 204.

## 2. **Validating Response Body**

Next, I focused on the content of the JSON response. To ensure the API returns correct data and in the expected format, I performed the following checks:

- **Presence of Required Numeric Fields**: I validated that the response contains numeric values for the fields `page`, `per_page`, `total`, and `total_pages`.
- **Valid 'data' Field**: I ensured that the `data` field exists and that it is an array (array of objects), and also checked that it isn't empty.
- **Object Properties in 'data'**: For each object in the `data` array, I validated that it contains the properties `id`, `email`, `first_name`, `last_name`, and `avatar`. These properties should be present with the correct data types (`id` as a number and the rest as non-empty strings).

## 3. **Extracting and Saving Data for Reuse**

To allow for reuse of extracted data in future requests, I saved some values in Postman environment variables:

- **Extracting User ID and Name**: From the response, which contained an array of users, I extracted the `id` and `first_name` of the user at index 1. These values were saved into the Postman environment for later use in other requests.
- **Sorting and Saving Names from Page 1**: After extracting names from the first page of results, I sorted them alphabetically and saved the sorted list in the environment.
- **Sorting and Combining Names from Two Pages**: After extracting names from both page 1 and page 2, I combined and sorted them for further processing.

## 4. **Validating Field Formats**

Next, I performed validation on the formats of certain important fields:

- **Email Format**: I checked that the emails in the responses match the standard email format using regular expressions.
- **Avatar Image Accessibility**: I sent a GET request to the avatar URL to check if the image is accessible and that the response is of the correct content type (an image).

## 5. **Validating 'Support' Field**

For responses that contained a `support` field, I checked if it had the required properties `url` and `text`, and ensured they were valid and not empty.

## 6. **Validating Response Headers**

Finally, I checked the response headers to ensure that they met certain expectations:

- I validated that the `X-Powered-By` header was set to "Express".
- I checked that the `Access-Control-Allow-Origin` header was set to "*" to allow access from all domains.

## 7. **Empty Response Validation**

For requests that shouldn't return any content (such as data deletion), I verified that:

- The response body is empty.
- The `Content-Length` header is 0.

## 8. **Testing for Missing User**

To handle cases where the requested user might not exist, I created tests to ensure that the response reflects the absence of the user:

- **Status Code 404 (Not Found)**: If the user is not found, the API should return a 404 status code, indicating that the requested resource does not exist.
- **Absence of 'data' Field**: I validated that the response does not contain the `data` field when the user is missing. This ensures that the API handles the case of a non-existing user properly.
- **Empty Response Body**: In some cases, the response body could be empty, so I ensured that the response body is empty (e.g., when attempting to fetch a user that doesn't exist).

## Conclusion

After completing these tests, I am confident that the API behaves as expected, returning the correct status codes, content, and data formats. Additionally, I was able to extract and store data for later use, sorting and combining user names from multiple pages. The tests ensure that the core functionality of the API is verified and guarantee its reliable and correct operation.
