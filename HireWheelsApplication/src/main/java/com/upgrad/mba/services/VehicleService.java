package com.upgrad.mba.services;

import com.upgrad.mba.entities.Vehicle;
import com.upgrad.mba.exceptions.VehicleDetailsNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleService {
    public Vehicle acceptVehicleDetails(Vehicle vehicle);
    public Vehicle getVehicleDetails(int id) throws VehicleDetailsNotFoundException;
    public Vehicle updateVehicleDetails(int id, Vehicle vehicle) throws VehicleDetailsNotFoundException;
    public boolean deleteVehicle(int id) throws VehicleDetailsNotFoundException;
    public List<Vehicle> getAllVehicleDetails();
    public Page<Vehicle> getPaginatedVehicleDetails(Pageable pageRequest);
}
