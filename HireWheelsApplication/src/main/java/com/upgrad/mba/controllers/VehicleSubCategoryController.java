package com.upgrad.mba.controllers;

import com.upgrad.mba.dto.VehicleSubCategoryDTO;
import com.upgrad.mba.entities.VehicleSubCategory;
import com.upgrad.mba.exceptions.VehiclesSubCategoryDetailsNotFoundException;
import com.upgrad.mba.services.VehicleSubCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/vehicle_app/v1")
public class VehicleSubCategoryController {
    @Autowired
    VehicleSubCategoryService vehicleSubCategoryService;

    @Autowired
    ModelMapper modelmapper;

    @GetMapping(value = "/vehicleSubCategory/{id}")
    public ResponseEntity getVehicleSubCategoryDetails(@PathVariable(name = "id") int id) throws VehiclesSubCategoryDetailsNotFoundException {
        VehicleSubCategory responseVehicleSubCategory = vehicleSubCategoryService.getVehicleSubCategoryDetails(id);
        VehicleSubCategoryDTO responseVehicleSubCategoryDTO = modelmapper.map(responseVehicleSubCategory, VehicleSubCategoryDTO.class);
        return new ResponseEntity<>(responseVehicleSubCategoryDTO, HttpStatus.OK);
    }


    @PostMapping(value="/vehicleSubCategory",consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newVehicleSubCategory(@RequestBody VehicleSubCategoryDTO vehicleSubCategoryDTO) {
        VehicleSubCategory newVehicleSubCategory = modelmapper.map(vehicleSubCategoryDTO, VehicleSubCategory.class);
        VehicleSubCategory savedVehicleSubCategory = vehicleSubCategoryService.acceptVehicleSubCategoryDetails(newVehicleSubCategory);
        VehicleSubCategoryDTO savedVehicleSubCategoryDTO = modelmapper.map(savedVehicleSubCategory, VehicleSubCategoryDTO.class);
        return new ResponseEntity<>(savedVehicleSubCategoryDTO, HttpStatus.CREATED);
    }
}
