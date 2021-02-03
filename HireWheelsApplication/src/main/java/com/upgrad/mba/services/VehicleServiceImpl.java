package com.upgrad.mba.services;

import com.upgrad.mba.dao.VehicleDao;
import com.upgrad.mba.entities.Vehicle;
import com.upgrad.mba.exceptions.VehicleDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    public VehicleDao vehicleDao;

    @Override
    public Vehicle acceptVehicleDetails(Vehicle vehicle) {
        return vehicleDao.save(vehicle);
    }

    @Override
    public Vehicle getVehicleDetails(int id) throws VehicleDetailsNotFoundException {
        return vehicleDao.findById(id)
                .orElseThrow(
                        () -> new VehicleDetailsNotFoundException("Vehicle not found for id: " + id)
                );
    }

    @Override
    public Vehicle updateVehicleDetails(int id, Vehicle vehicle) throws VehicleDetailsNotFoundException {
        Vehicle savedVehicle = getVehicleDetails(id);

        if (isNotNullOrZero(vehicle.getVehicleName())) {
            savedVehicle.setVehicleName(vehicle.getVehicleName());
        }
        if (isNotNullOrZero(vehicle.getVehicleDescription())) {
            savedVehicle.setVehicleDescription(vehicle.getVehicleDescription());
        }
        if (isNotNullOrZero(vehicle.getReleaseDate())) {
            savedVehicle.setReleaseDate(vehicle.getReleaseDate());
        }
        if (isNotNullOrZero(vehicle.getDuration())) {
            savedVehicle.setDuration(vehicle.getDuration());
        }
        if (isNotNullOrZero(vehicle.getCoverPhotoUrl())) {
            savedVehicle.setCoverPhotoUrl(vehicle.getCoverPhotoUrl());
        }
        if (isNotNullOrZero(vehicle.getTrailerUrl())) {
            savedVehicle.setTrailerUrl(vehicle.getTrailerUrl());
        }
        if (isNotNullOrZero(vehicle.getStatus())) {
            savedVehicle.setStatus(vehicle.getStatus());
        }

        return vehicleDao.save(savedVehicle);
    }

    private boolean isNotNullOrZero(Object obj) {
        return obj != null;
    }

    private boolean isNotNullOrZero(int val) {
        return val != 0;
    }

    @Override
    public boolean deleteVehicle(int id) throws VehicleDetailsNotFoundException {
        Vehicle savedVehicle = getVehicleDetails(id);
        vehicleDao.delete(savedVehicle);
        return true;
    }

    @Override
    public List<Vehicle> getAllVehicleDetails() {
        return vehicleDao.findAll();
    }

    @Override
    public Page<Vehicle> getPaginatedVehicleDetails(Pageable pageRequest) {
        return vehicleDao.findAll(pageRequest);
    }
}
