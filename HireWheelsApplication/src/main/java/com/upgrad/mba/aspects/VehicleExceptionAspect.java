package com.upgrad.mba.aspects;

import com.upgrad.mba.exceptions.VehicleDetailsNotFoundException;
import com.upgrad.mba.responses.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VehicleExceptionAspect {
    @ExceptionHandler(VehicleDetailsNotFoundException.class)
    public ResponseEntity<CustomResponse> handleVehicleDetailsNotFoundException(Exception e){
        CustomResponse response = new CustomResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
        return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
