package com.madhu.api.pojos;

public class BookingResponse {
  private Booking booking;
  private Integer bookingid;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Integer getBookingID() {
        return bookingid;
    }

    public void setBookingID(Integer bookingID) {
        this.bookingid = bookingID;
    }
}
