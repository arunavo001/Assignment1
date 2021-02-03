package com.upgrad.mba.services;

import com.upgrad.mba.entities.VehicleCategory;
import com.upgrad.mba.exceptions.*;

import java.util.List;

public interface VehicleCategoryService {
    public VehicleCategory acceptVehicleCategoryDetails(VehicleCategory vehicleCategory) throws VehicleDetailsNotFoundException, VehiclesSubCategoryDetailsNotFoundException;
    public VehicleCategory getVehicleCategoryDetails(int id) throws VehicleCategoryDetailsNotFoundException;
    public boolean deleteVehicleCategory(int id) throws VehicleCategoryDetailsNotFoundException;
    public List<VehicleCategory> getAllVehicleCategoryDetails();
}
