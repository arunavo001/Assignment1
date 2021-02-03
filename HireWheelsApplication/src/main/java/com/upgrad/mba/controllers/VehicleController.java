package com.upgrad.mba.controllers;

import com.upgrad.mba.dto.VehicleDTO;
import com.upgrad.mba.entities.Vehicle;
import com.upgrad.mba.exceptions.APIException;
import com.upgrad.mba.exceptions.VehicleDetailsNotFoundException;
import com.upgrad.mba.exceptions.StatusDetailsNotFoundException;
import com.upgrad.mba.services.VehicleService;
import com.upgrad.mba.validator.VehicleValidator;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/vehicle_app/v1")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;

    @Autowired
    VehicleValidator vehicleValidator;

    @Autowired
    ModelMapper modelmapper;

    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    @GetMapping(value= {"/sayHelloVehicle"})
    public String sayHello(){
        logger.info("Hello from the VehicleController");
        return "Hello World To All From VehicleController";
    }

    @GetMapping(value = "/vehicles/{id}")
    public ResponseEntity getVehicleDetails(@PathVariable(name = "id") int id) throws VehicleDetailsNotFoundException {
        Vehicle responseVehicle = vehicleService.getVehicleDetails(id);
        VehicleDTO responseVehicleDTO = modelmapper.map(responseVehicle, VehicleDTO.class);
        logger.debug("Get vehicle details :" + responseVehicle);
        return new ResponseEntity<>(responseVehicleDTO, HttpStatus.OK);
    }

    @GetMapping(value="/vehicles",produces= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity getAllVehicles() {
        List<Vehicle> vehicleList = vehicleService.getAllVehicleDetails();
        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        for(Vehicle vehicle : vehicleList){
            vehicleDTOList.add(modelmapper.map(vehicle, VehicleDTO.class));
        }
        logger.debug("Returning all vehicles" , vehicleDTOList);
        return new ResponseEntity<>(vehicleDTOList, HttpStatus.OK);
    }

    @PostMapping(value="/vehicles", consumes = MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity newVehicle(@RequestBody VehicleDTO vehicleDTO) throws APIException, ParseException, StatusDetailsNotFoundException {
        ResponseEntity responseEntity = null;
        try {
            vehicleValidator.validateVehicle(vehicleDTO);
            Vehicle newVehicle = modelmapper.map(vehicleDTO, Vehicle.class);
            Vehicle savedVehicle = vehicleService.acceptVehicleDetails(newVehicle);
            VehicleDTO savedVehicleDTO = modelmapper.map(savedVehicle, VehicleDTO.class);
            responseEntity = new ResponseEntity<>(savedVehicleDTO,HttpStatus.CREATED);
            logger.debug("Accept new vehicle details",responseEntity);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("Exception:" , e);
        }
        return responseEntity;
    }

    @PutMapping(value="/vehicles/{id}",consumes= MediaType.APPLICATION_JSON_VALUE,headers="Accept=application/json")
    public ResponseEntity updateVehicleDetails(@PathVariable(name = "id") int id, @RequestBody VehicleDTO vehicleDTO) throws VehicleDetailsNotFoundException, APIException, ParseException, StatusDetailsNotFoundException {
        logger.debug("Update vehicle details : vehicle id :" + id, vehicleDTO);
        vehicleValidator.validateVehicle(vehicleDTO);
        Vehicle newVehicle = modelmapper.map(vehicleDTO, Vehicle.class);
        Vehicle updatedVehicle = vehicleService.updateVehicleDetails(id, newVehicle);
        VehicleDTO updatedVehicleDTO = modelmapper.map(updatedVehicle, VehicleDTO.class);
        return new ResponseEntity<>(updatedVehicleDTO,HttpStatus.OK);
    }
}
