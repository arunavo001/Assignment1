package com.upgrad.mba.validator;

import com.upgrad.mba.dto.VehicleDTO;
import com.upgrad.mba.exceptions.APIException;
import com.upgrad.mba.exceptions.StatusDetailsNotFoundException;

import java.text.ParseException;

public interface VehicleValidator {
    public void validateVehicle(VehicleDTO vehicleDTO) throws APIException, ParseException, StatusDetailsNotFoundException;
}
