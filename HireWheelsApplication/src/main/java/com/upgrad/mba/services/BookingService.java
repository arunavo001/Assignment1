package com.upgrad.mba.services;

import com.upgrad.mba.entities.Booking;
import com.upgrad.mba.exceptions.BookingDetailsNotFoundException;
import com.upgrad.mba.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mba.exceptions.VehicleCategoryDetailsNotFoundException;

import java.util.List;

public interface BookingService {
    public Booking acceptBookingDetails(Booking booking) throws VehicleCategoryDetailsNotFoundException, CustomerDetailsNotFoundException;
    public Booking getBookingDetails(int id) throws BookingDetailsNotFoundException;
    public boolean deleteBooking(int id) throws BookingDetailsNotFoundException;
    public List<Booking> getAllBookingDetails();
}
