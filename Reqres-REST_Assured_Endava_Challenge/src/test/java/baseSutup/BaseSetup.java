package baseSutup;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import testData.TestData;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static ReqRes.api.tests.Constants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;
import static org.testng.Assert.assertTrue;
import static testData.TestData.*;


public class BaseSetup {

    protected TestData testData = new TestData();


    public void getListUsersPage1() {

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(LIST_USERS_PAGE_1_ENDPOINT);
        String responseBody = response.getBody().asString();

        //response.prettyPrint();

        assertThat("Status Code should be 200", response.getStatusCode(), equalTo(200));


        assertThat("Response should contain 'page'", responseBody, containsString("page"));
        assertThat("Response should contain 'per_page'", responseBody, containsString("per_page"));
        assertThat("Response should contain 'total'", responseBody, containsString("total"));
        assertThat("Response should contain 'total_pages'", responseBody, containsString("total_pages"));
        assertThat("Response should contain 'data'", responseBody, containsString("data"));

        List<Map<String, Object>> data = response.jsonPath().getList("data");
        assertThat("Data should not be empty", data, not(empty()));

        List<Map<String, Object>> sortedDataPage1 = data.stream()
                .sorted(Comparator.comparing(a -> ((String) a.get("first_name"))))
                .collect(Collectors.toList());

        TestData.setUserNamesPage1(sortedDataPage1);

        userId = (int) data.get(1).get("id");
        firstName = (String) data.get(1).get("first_name");

        System.out.println("Extracted and saved user ID for reuse in other requests: " +"\u001B[32m" + userId + "\u001B[0m");
        System.out.println("Extracted and saved First Name for reuse in other requests: " +"\u001B[32m" + firstName + "\u001B[0m");
        System.out.println("----------------------------------");
        System.out.println();

        data.forEach(item -> {
            assertThat("Each item should have 'id'", item, hasKey("id"));
            assertThat("Each item should have 'email'", item, hasKey("email"));
            assertThat("Each item should have 'first_name'", item, hasKey("first_name"));
            assertThat("Each item should have 'last_name'", item, hasKey("last_name"));
            assertThat("Each item should have 'avatar'", item, hasKey("avatar"));
        });


    }



    public void getListUsersPage2 () {

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(LIST_USERS_PAGE_2_ENDPOINT);


        assertThat("Status Code should be 200", response.getStatusCode(), equalTo(200));
        String responseBody = response.getBody().asString();


        assertThat("Response should contain 'page'", responseBody, containsString("page"));
        assertThat("Response should contain 'per_page'", responseBody, containsString("per_page"));
        assertThat("Response should contain 'total'", responseBody, containsString("total"));
        assertThat("Response should contain 'total_pages'", responseBody, containsString("total_pages"));
        assertThat("Response should contain 'data'", responseBody, containsString("data"));

        List<Map<String, Object>> data = response.jsonPath().getList("data");

        List<Map<String, Object>> sortedDataPage2 = data.stream()
                .sorted(Comparator.comparing(a -> ((String) a.get("first_name"))))
                .collect(Collectors.toList());

        TestData.setUserNamesPage2(sortedDataPage2);

    }

    public void combineAndSortUsers() {

        List<Map<String, Object>> sortedDataPage1 = TestData.getUserNamesPage1();
        List<Map<String, Object>> sortedDataPage2 = TestData.getUserNamesPage2();

        sortedDataPage1.addAll(sortedDataPage2);

        sortedDataPage1.sort(Comparator.comparing(a -> ((String) a.get("first_name"))));

        System.out.println("Sorted and Combined User Data:");
        sortedDataPage1.forEach(user -> System.out.println("ID:" + user.get("id") + " " + "\u001B[32m"
                + user.get("first_name")+ "\u001B[0m"  + " " + user.get("last_name") + " (" + user.get("email") + ")"));

        for (int i = 0; i < sortedDataPage1.size() - 1; i++) {
            String firstName = (String) sortedDataPage1.get(i).get("first_name");
            String nextFirstName = (String) sortedDataPage1.get(i + 1).get("first_name");
            assertTrue(firstName.compareTo(nextFirstName) <= 0, "List is not sorted correctly");
        }
    }

    public void singleUserDetails(){

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(SINGLE_USER_DETAILS_ENDPOINT + userId );


        assertThat("Status Code should be 200. This test will fail if run alone because the user ID must first be" +
                " generated by the user creation test. Please run testng.xml ", response.getStatusCode(), equalTo(200));

        assertThat("Response should contain 'data' field", response.jsonPath().get("data"), notNullValue());
        assertThat("Data should be an object", response.jsonPath().get("data"), instanceOf(java.util.Map.class));

        assertThat("ID should be a number", response.jsonPath().getInt("data.id"), instanceOf(Integer.class));
        assertThat("Email should be a non-empty string", response.jsonPath().getString("data.email"), not(emptyString()));
        assertThat("First name should be a non-empty string", response.jsonPath().getString("data.first_name"), not(emptyString()));
        assertThat("Last name should be a non-empty string", response.jsonPath().getString("data.last_name"), not(emptyString()));
        assertThat("Avatar should be a non-empty string", response.jsonPath().getString("data.avatar"), not(emptyString()));

        assertThat("Response should contain 'support' field", response.jsonPath().get("support"), notNullValue());
        assertThat("Support should be an object", response.jsonPath().get("support"), instanceOf(java.util.Map.class));

        assertThat("Support should have URL", response.jsonPath().getString("support.url"), not(emptyString()));
        assertThat("Support should have text", response.jsonPath().getString("support.text"), not(emptyString()));

        String email = response.jsonPath().getString("data.email");
        String emailRegex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        assertThat("Email should match the correct format", email, matchesPattern(emailRegex));

        String avatarUrl = response.jsonPath().getString("data.avatar");
        Response avatarResponse = RestAssured.given().get(avatarUrl);

        assertThat("Avatar image should be accessible", avatarResponse.getStatusCode(), equalTo(200));

        String contentType = avatarResponse.getHeader("Content-Type");
        assertThat("Avatar should be a valid image format", contentType, containsString("image"));
        System.out.println();
        System.out.println("The request for extracted user Details was successfully executed. All fields are available," +
                " and the avatar was verified with a separate request.");
    }


public void invalidUserIdDetails(){


    Response response = given()
            .header("Content-Type", "application/json")
            .when()
            .get(SINGLE_USER_NOT_FOUND_ENDPOINT + invalidId );


    assertThat("Status Code should be 404", response.getStatusCode(), equalTo(404));

    assertThat("Response should not contain 'data' field", response.getBody().jsonPath().get("data"), is(emptyOrNullString()));
    assertThat("Response should not contain 'id' in 'data'", response.getBody().jsonPath().get("data.id"), is(nullValue()));
    assertThat("Response should not contain 'email' in 'data'", response.getBody().jsonPath().get("data.email"), is(nullValue()));
    assertThat("Response should not contain 'first_name' in 'data'", response.getBody().jsonPath().get("data.first_name"), is(nullValue()));
    assertThat("Response should not contain 'last_name' in 'data'", response.getBody().jsonPath().get("data.last_name"), is(nullValue()));
    assertThat("Response should not contain 'avatar' in 'data'", response.getBody().jsonPath().get("data.avatar"), is(nullValue()));

    System.out.println();
    System.out.println("The response from the request 'invalidUserIdDetails' is empty, indicating that there is no user with the specified ID." +
            "The absence of fields such as 'data', 'id', 'email', 'first_name', 'last_name', and 'avatar' has been validated.");


}

public void createUser(){

    String requestBody = "{"
            + "\"name\": \"" + testData.randomName + "\","
            + "\"job\": \"visitor\""
            + "}";

    Response response = given()
            .header("Content-Type", "application/json")
            .body(requestBody)
            .when()
            .post(CREATE_USER_ENDPOINT);

    assertThat("Status Code should be 201", response.getStatusCode(), equalTo(201));

    createdUniqueUserID = Integer.parseInt(response.jsonPath().getString("id"));

    userName = response.jsonPath().getString("name");
    assertThat("Name should match the expected value", userName, equalTo(testData.randomName));

    String createdAt = response.jsonPath().getString("createdAt");
    String iso8601Regex = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d+)?Z$";
    assertThat("CreatedAt should match ISO 8601 format", createdAt, matchesPattern(iso8601Regex));

    System.out.println();
    System.out.println("User created successfully with " +
            "ID: " +"\u001B[32m" + createdUniqueUserID + "\u001B[0m" + " and Name: " +"\u001B[32m" + userName + "\u001B[0m");

}

public void deleteUser() {

    Response response = given()
            .header("Content-Type", "application/json")
            .when()
            .delete(DELETE_USER_ENDPOINT + createdUniqueUserID);

//    response.prettyPrint();
//    System.out.println("Response Headers: " + response.getHeaders());
//    System.out.println("Content-Length Header: " + response.getHeader("Content-Length"));
//    System.out.println("X-Powered-By Header: " + response.getHeader("X-Powered-By"));
//    System.out.println("Access-Control-Allow-Origin Header: " + response.getHeader("Access-Control-Allow-Origin"));


    assertThat("Status Code should be 204", response.getStatusCode(), equalTo(204));

    assertThat("Response body should be empty", response.getBody().asString(), equalTo(""));

    assertThat("Content-Length should be 0", response.getHeader("Content-Length"), equalTo("0"));

    assertThat("X-Powered-By should be 'Express'", response.getHeader("X-Powered-By"), equalTo("Express"));
    assertThat("Access-Control-Allow-Origin should be '*'", response.getHeader("Access-Control-Allow-Origin"), equalTo("*"));

    System.out.println();
    System.out.println("User with " +
            "ID: " +"\u001B[32m" + createdUniqueUserID + "\u001B[0m" + " and Name: " +"\u001B[32m" + userName +
            "\u001B[0m" + " deleted successfully");

}




    protected void addIssueAndLink(String issueId, String taskName, String taskUrl) {
        Allure.link(taskName, taskUrl);
        Allure.issue(issueId, taskUrl);
    }
}
