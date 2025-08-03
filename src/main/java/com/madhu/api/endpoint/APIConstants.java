package com.madhu.api.endpoint;

public class APIConstants {

    // Base URL for the RESTful Booker API
    public static final String BASE_URL = "https://restful-booker.herokuapp.com";

    // Auth Endpoint
    public static final String CREATE_TOKEN = "/auth";

    // Booking Endpoints
    public static final String CREATE_BOOKING = "/booking";
    public static final String GET_BOOKING_BY_ID = "/booking";
    public static final String UPDATE_BOOKING = "/booking";
    public static final String DELETE_BOOKING = "/booking";
    public static final String GET_ALL_BOOKINGS = "/booking";

    // Health Check
    public static final String PING = "/ping";

    private APIConstants() {
        // Private constructor to prevent instantiation
    }
}


