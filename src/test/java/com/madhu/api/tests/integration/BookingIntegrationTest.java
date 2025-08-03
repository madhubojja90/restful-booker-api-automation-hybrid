package com.madhu.api.tests.integration;

import com.madhu.api.base.BaseTest;
import com.madhu.api.endpoint.APIConstants;
import com.madhu.api.pojos.Booking;
import com.madhu.api.pojos.BookingResponse;
import com.madhu.api.pojos.TokenResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookingIntegrationTest extends BaseTest {

    @Owner("madhu.bojja")
    @Description("Create a booking and validate response")
    @Test(groups = "integration" ,priority = 1)
    public void shouldCreateBookingSuccessfully(ITestContext context) {
       context.setAttribute("token",getToken());

        System.out.println("Token - "+context.getAttribute("token"));
        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response= RestAssured
                .given(requestSpecification)
                .when().body(payloadmanager.createBookingPayload()).post();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(Matchers.equalTo(200));
        BookingResponse bookingResponse=payloadmanager.bookingResponse(response.asString());

        assertThat(bookingResponse.getBookingID()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("madhu");
        assertThat(bookingResponse.getBooking().getLastname()).isEqualTo("Bojja");
        assertThat(bookingResponse.getBooking().getTotalprice()).isEqualTo(101);
        assertThat(bookingResponse.getBooking().getDepositpaid()).isEqualTo(true);
        assertThat(bookingResponse.getBooking().getBookingdates().getCheckin()).isEqualTo("2025-08-02");
        assertThat(bookingResponse.getBooking().getBookingdates().getCheckout()).isEqualTo("2025-08-30");
        assertThat(bookingResponse.getBooking().getAdditionalneeds()).isEqualTo("Breakfast");

        context.setAttribute("bookingID",bookingResponse.getBookingID());
    }

    @Owner("madhu.bojja")
    @Description("Get booking by ID and validate details")
    @Test(groups = "integration" ,priority = 2)
    public void shouldRetrieveBookingById(ITestContext context) {
        System.out.println("Token - "+context.getAttribute("token"));

        requestSpecification.basePath(APIConstants.GET_BOOKING_BY_ID+"/"+context.getAttribute("bookingID"));
        response= RestAssured
                .given(requestSpecification)
                .when().log().all().get();
        System.out.println(response.asString());
        validatableResponse=response.then().log().all();

        Booking booking=payloadmanager.getResponsefromJson(response.asString());
        //assertThat(bookingResponse.getBookingID()).isNotNull();
        assertThat(booking.getFirstname()).isEqualTo("madhu");
        assertThat(booking.getLastname()).isEqualTo("Bojja");
        assertThat(booking.getTotalprice()).isEqualTo(101);
        assertThat(booking.getDepositpaid()).isEqualTo(true);
        assertThat(booking.getBookingdates().getCheckin()).isEqualTo("2025-08-02");
        assertThat(booking.getBookingdates().getCheckout()).isEqualTo("2025-08-30");
        assertThat(booking.getAdditionalneeds()).isEqualTo("Breakfast");
    }

    @Owner("madhu.bojja")
    @Description("Update existing booking and verify the changes")
    @Test(groups = "integration" ,priority = 3)
    public void shouldUpdateBookingDetailsById(ITestContext context) {
        System.out.println("Token - "+context.getAttribute("token"));

        requestSpecification.basePath(APIConstants.UPDATE_BOOKING+"/"+context.getAttribute("bookingID"));
        requestSpecification.cookie("token",context.getAttribute("token").toString());
        response= RestAssured
                .given(requestSpecification)
                .when().body(payloadmanager.fullUpdateBookingPayload()).put();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(Matchers.equalTo(200));
        BookingResponse bookingResponse=payloadmanager.bookingResponse(response.asString());

        Booking booking=payloadmanager.getResponsefromJson(response.asString());
        //assertThat(bookingResponse.getBookingID()).isNotNull();
        assertThat(booking.getFirstname()).isEqualTo("Akhila");
        assertThat(booking.getLastname()).isEqualTo("Choppari");
        assertThat(booking.getTotalprice()).isEqualTo(101);
        assertThat(booking.getDepositpaid()).isEqualTo(true);
        assertThat(booking.getBookingdates().getCheckin()).isEqualTo("2025-08-02");
        assertThat(booking.getBookingdates().getCheckout()).isEqualTo("2025-08-30");
        assertThat(booking.getAdditionalneeds()).isEqualTo("Breakfast");
    }

    @Owner("madhu.bojja")
    @Description("Delete a booking and verify removal")
    @Test(groups = "integration" ,priority = 4)
    public void shouldDeleteBookingSuccessfullyById(ITestContext context) {

        System.out.println("Token - "+context.getAttribute("token"));

        requestSpecification.basePath(APIConstants.DELETE_BOOKING+"/"+context.getAttribute("bookingID"));
        requestSpecification.cookie("token",context.getAttribute("token").toString());
        response= RestAssured
                .given(requestSpecification)
                .when().body(payloadmanager.fullUpdateBookingPayload()).delete();
        validatableResponse=response.then().log().all();
        validatableResponse.statusCode(Matchers.equalTo(201));
    }

}
