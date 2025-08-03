package com.madhu.api.modules;


import com.google.gson.Gson;
import com.madhu.api.pojos.*;
import com.madhu.api.utils.ConfigReader;


public class Payloadmanager {

    Gson gson = new Gson();

    public String createBookingPayload() {
        Booking booking = new Booking();
        booking.setFirstname("madhu");
        booking.setLastname("Bojja");
        booking.setTotalprice(101);
        booking.setDepositpaid(true);
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2025-08-02");
        bookingDates.setCheckout("2025-08-30");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");
        return gson.toJson(booking);
    }

    public String createBookingInvalidPayload()
    {
        return "{ }";
    }

    public String updateBookingPayload()
    {
        Booking booking = new Booking();
        booking.setFirstname("Akhila");
        booking.setLastname("Choppari");
        booking.setTotalprice(101);
        booking.setDepositpaid(true);
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2025-08-02");
        bookingDates.setCheckout("2025-08-30");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");
        return gson.toJson(booking);
    }
    public String fullUpdateBookingPayload() {
        Booking booking = new Booking();
        booking.setFirstname("Akhila");
        booking.setLastname("Choppari");
        booking.setTotalprice(101);
        booking.setDepositpaid(true);
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2025-08-02");
        bookingDates.setCheckout("2025-08-30");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast");
        return gson.toJson(booking);
    }

    public BookingResponse bookingResponse(String responseString)
    {
        BookingResponse bookingResponse=gson.fromJson(responseString,BookingResponse.class);
        return bookingResponse;
    }

    public String setAuthToken()
    {
        Auth auth=new Auth();
        auth.setUsername(ConfigReader.getProperty("username"));
        auth.setPassword(ConfigReader.getProperty("password"));
        return gson.toJson(auth);
    }

    public String getTokenFromJson(String tokenResponse)
    {
        TokenResponse tokenResponse1=gson.fromJson(tokenResponse,TokenResponse.class);
        return tokenResponse1.getToken();
    }
    public Booking  getResponsefromJson(String bookResponse)
    {
        Booking booking=gson.fromJson(bookResponse,Booking.class);
        return booking;
    }
}



