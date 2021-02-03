package com.upgrad.mba.validator;

import com.upgrad.mba.dto.VehicleDTO;
import com.upgrad.mba.exceptions.APIException;
import com.upgrad.mba.exceptions.StatusDetailsNotFoundException;
import com.upgrad.mba.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;


@Service
public class VehicleValidatorImpl implements VehicleValidator {
    @Autowired
    StatusService statusService;

    public void validateVehicle(VehicleDTO vehicleDTO) throws APIException, ParseException, StatusDetailsNotFoundException {

        if(vehicleDTO.getVehicleName() == null || vehicleDTO.getVehicleName().length() <= 0)
            throw new APIException("Invalid vehicle name");
        if(vehicleDTO.getCoverPhotoUrl() == null || vehicleDTO.getCoverPhotoUrl().length() <=0)
            throw new APIException("Invalid cover url");
        if(vehicleDTO.getTrailerUrl() == null || vehicleDTO.getTrailerUrl().length() <=0)
            throw new APIException("Invalid trial url");
        if(vehicleDTO.getVehicleDescription() == null || vehicleDTO.getVehicleDescription().length() <=0)
            throw new APIException("Invalid  description");
        if(vehicleDTO.getDuration() <= 25 || vehicleDTO.getDuration() > 240)
            throw new APIException("Invalid duration");
        if(vehicleDTO.getStatusId() <= 0)
            throw new APIException("Invalid status");
        if(statusService.getStatusDetails(vehicleDTO.getStatusId()).getStatusName().equalsIgnoreCase("Upcoming")){
            if(vehicleDTO.getReleaseDate().compareTo(LocalDateTime.now()) <0 ){
                throw new APIException("Invalid vehicle release date");
            }
        }
        if(statusService.getStatusDetails(vehicleDTO.getStatusId()).getStatusName().equalsIgnoreCase("Released")){
            if(vehicleDTO.getReleaseDate().compareTo(LocalDateTime.now())>0){
                throw new APIException("Invalid vehicle release date");
            }
        }
        if(statusService.getStatusDetails(vehicleDTO.getStatusId()).getStatusName().equalsIgnoreCase("Blocked")){
            throw new APIException("Invalid vehicle status");
        }
    }
}
