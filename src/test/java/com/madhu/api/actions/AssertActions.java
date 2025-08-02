package com.madhu.api.actions;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AssertActions {

    // Validate numeric response values with description
    public void verifyResponseBody(float actual, float expected, String description) {
        assertEquals(actual, expected, description);
    }

    public void verifyResponseBody(int actual, int expected, String description) {
        assertEquals(actual, expected, description);
    }

    public void verifyResponseBody(double actual, double expected, String description) {
        assertEquals(actual, expected, description);
    }

    public void verifyResponseBody(String actual, String expected, String description) {
        assertEquals(actual, expected, description);
    }

    // Custom assertion to check if status code is in 5xx range
    public void verifyStatusCodeInvalidReq(Response response) {
        String statusCode = String.valueOf(response.getStatusCode());
        assertTrue(statusCode.startsWith("5"),
                "Expected 5xx error. Actual status code: " + statusCode);
    }

    // General status code validator
    public void verifyStatusCode(Response response, int expectedStatusCode) {
        assertEquals(response.getStatusCode(), expectedStatusCode,
                "Status code mismatch!");
    }
}


