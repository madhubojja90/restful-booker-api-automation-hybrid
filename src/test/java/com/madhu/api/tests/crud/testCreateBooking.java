package com.madhu.api.tests.crud;

import com.madhu.api.base.BaseTest;
import com.madhu.api.endpoint.APIConstants;
import com.madhu.api.pojos.Booking;
import com.madhu.api.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;



public class testCreateBooking extends BaseTest {

    @Test(groups ="smoke")
    @Owner("madhu")
    @Description("TC1-Create Booking")
    @Severity(SeverityLevel.NORMAL)
    public void createBooking_ShouldReturn200_WhenIsValid()
    {
        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response=RestAssured
                .given(requestSpecification)
                .when().body(payloadmanager.createBookingPayload()).post();
        validatableResponse=response.then();
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


    }
    @Test(groups ="smoke")
    @Owner("madhu")
    @Description("TC1-Create Booking")
    @Severity(SeverityLevel.NORMAL)
    public void createBooking_ShouldReturn500_WhenInValid()
    {
        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        response=RestAssured
                .given(requestSpecification)
                .when().body(payloadmanager.createBookingInvalidPayload()).post();
        validatableResponse=response.then();
        validatableResponse.statusCode(Matchers.equalTo(500));

    }
}
