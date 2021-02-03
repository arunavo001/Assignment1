package com.upgrad.mba.controllers;

import com.upgrad.mba.dto.BookingDTO;
import com.upgrad.mba.entities.Booking;
import com.upgrad.mba.entities.Vehicle;
import com.upgrad.mba.entities.VehicleCategory;
import com.upgrad.mba.exceptions.*;
import com.upgrad.mba.services.BookingService;
import com.upgrad.mba.services.VehicleCategoryService;
import com.upgrad.mba.utils.DTOEntityConverter;
import com.upgrad.mba.utils.EntityDTOConverter;
import com.upgrad.mba.validator.BookingValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value="/wheels_app/v1")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @Autowired
    VehicleCategoryService vehicleCategoryService;

    @Autowired
    ModelMapper modelmapper;

    @Autowired
    EntityDTOConverter entityDTOConverter;

    @Autowired
    DTOEntityConverter dtoEntityConverter;

    @Autowired
    BookingValidator bookingValidator;

    @GetMapping(value = "/bookings/{id}")
    public ResponseEntity getBookingDetails(@PathVariable(name = "id") int id) throws BookingDetailsNotFoundException {
        Booking responseBooking = bookingService.getBookingDetails(id);
        BookingDTO responseBookingDTO = modelmapper.map(responseBooking,BookingDTO.class);
        return new ResponseEntity<>(responseBookingDTO, HttpStatus.OK);
    }

    @GetMapping(value="/bookings",produces= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity findAllBookings() {
        List<Booking> bookings = bookingService.getAllBookingDetails();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PostMapping(value="/bookings",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newBooking(@RequestBody BookingDTO bookingDTO) throws CustomerDetailsNotFoundException, VehicleCategoryDetailsNotFoundException, APIException, BookingFailedException {
        ResponseEntity responseEntity = null;
        try {
            bookingValidator.validateBooking(bookingDTO);
            VehicleCategory vehicleCategory = vehicleCategoryService.getVehicleCategoryDetails(bookingDTO.getVehicleCategoryId());
            Vehicle bookedVehicle = vehicleCategory.getVehicle();
            if(bookedVehicle == null){
                throw new BookingFailedException("Vehicle details not found");
            }else{
                if(!bookedVehicle.getStatus().getStatusName().equalsIgnoreCase("Released"))
                    throw new BookingFailedException("Vehicle is not released");
            }
            Booking newBooking = dtoEntityConverter.convertToBookingEntity(bookingDTO);
            Booking savedBooking = bookingService.acceptBookingDetails(newBooking);
            BookingDTO savedBookingDTO = entityDTOConverter.convertToBookingDTO(savedBooking);
            responseEntity = new ResponseEntity<>(savedBookingDTO, HttpStatus.CREATED);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<String> removeBookingDetails(@PathVariable("id") int id) throws BookingDetailsNotFoundException{
        bookingService.deleteBooking(id);
        return new ResponseEntity<>("Booking details successfully removed ",HttpStatus.OK);
    }
}
