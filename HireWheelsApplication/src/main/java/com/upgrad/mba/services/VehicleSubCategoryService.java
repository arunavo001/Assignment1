package com.upgrad.mba.services;

import com.upgrad.mba.entities.VehicleSubCategory;
import com.upgrad.mba.exceptions.VehiclesSubCategoryDetailsNotFoundException;

import java.util.List;

public interface VehicleSubCategoryService {
    public VehicleSubCategory acceptVehicleSubCategoryDetails(VehicleSubCategory vehicleSubCategory);
    public VehicleSubCategory getVehicleSubCategoryDetails(int id) throws VehiclesSubCategoryDetailsNotFoundException;
    public VehicleSubCategory updateVehicleSubCategoryDetails(int id, VehicleSubCategory vehicleSubCategory) throws VehiclesSubCategoryDetailsNotFoundException;
    public boolean deleteVehicleSubCategory(int id) throws VehiclesSubCategoryDetailsNotFoundException;
    public List<VehicleSubCategory> getAllVehicleSubCategoryDetails();
}
