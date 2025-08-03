package com.madhu.api.base;

import com.madhu.api.actions.AssertActions;
import com.madhu.api.endpoint.APIConstants;
import com.madhu.api.modules.Payloadmanager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

import java.sql.SQLOutput;

public class BaseTest {

    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;
    public AssertActions assertActions;
    public Payloadmanager payloadmanager;
    public JsonPath jsonPath;

    @BeforeTest
    public void setConfig() {
        System.out.println("Before Test");
        payloadmanager = new Payloadmanager();
        assertActions = new AssertActions();
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .setContentType(ContentType.JSON)
                .build().log().all();
        /* Second Method for RequestSpecification

        requestSpecification= RestAssured
        .given().baseUri(APIConstants.BASE_URL)
         .contentType(ContentType.JSON).log().all();
       */
    }

        public String getToken ()
        {
            requestSpecification = new RequestSpecBuilder()
                    .setBaseUri(APIConstants.BASE_URL)
                    .setBasePath(APIConstants.CREATE_TOKEN)
                    .setContentType(ContentType.JSON).build().log().all();
            response = RestAssured
                    .given(requestSpecification)
                    .when().body(payloadmanager.setAuthToken()).post();
            String token= payloadmanager.getTokenFromJson(response.asString());

            return token;

        }


    }

