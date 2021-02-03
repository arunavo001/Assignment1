package com.upgrad.mba.utils;

import com.upgrad.mba.dto.BookingDTO;
import com.upgrad.mba.entities.Booking;
import com.upgrad.mba.exceptions.CustomerDetailsNotFoundException;
import com.upgrad.mba.exceptions.VehicleCategoryDetailsNotFoundException;
import com.upgrad.mba.services.CustomerService;
import com.upgrad.mba.services.VehicleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DTOEntityConverter {
    @Autowired
    CustomerService customerService;

    @Autowired
    VehicleCategoryService vehicleCategoryService;

    public Booking convertToBookingEntity(BookingDTO bookingDTO) throws CustomerDetailsNotFoundException, VehicleCategoryDetailsNotFoundException {
        Booking booking = new Booking();
        booking.setNoOfSeats(bookingDTO.getNoOfSeats());
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setCustomer(customerService.getCustomerDetails(bookingDTO.getCustomerId()));
        booking.setVehicleCategory(vehicleCategoryService.getVehicleCategoryDetails(bookingDTO.getVehicleCategoryId()));
        return booking;
    }
}