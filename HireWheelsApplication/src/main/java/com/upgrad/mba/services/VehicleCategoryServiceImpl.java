package com.upgrad.mba.services;

import com.upgrad.mba.dao.VehicleCategoryDao;
import com.upgrad.mba.entities.VehicleCategory;
import com.upgrad.mba.exceptions.VehicleDetailsNotFoundException;
import com.upgrad.mba.exceptions.VehicleCategoryDetailsNotFoundException;
import com.upgrad.mba.exceptions.VehiclesSubCategoryDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleCategoryServiceImpl implements VehicleCategoryService {

    @Autowired
    private VehicleCategoryDao vehicleCategoryDao;

    @Autowired
    private VehicleSubCategoryService vehicleSubCategoryService;

    @Autowired
    private VehicleService vehicleService;

    @Override
    public VehicleCategory acceptVehicleCategoryDetails(VehicleCategory vehicleCategory)
            throws VehicleDetailsNotFoundException, VehiclesSubCategoryDetailsNotFoundException {
        vehicleService.getVehicleDetails(vehicleCategory.getVehicle().getVehicleId());
        vehicleService.getVehicleDetails(vehicleCategory.getVehicle().getVehicleId());
        return vehicleCategoryDao.save(vehicleCategory);
    }

    @Override
    public VehicleCategory getVehicleCategoryDetails(int id) throws VehicleCategoryDetailsNotFoundException {
        return vehicleCategoryDao.findById(id)
                .orElseThrow(
                        () -> new VehicleCategoryDetailsNotFoundException("VehicleCategory not found by id: " + id)
                );
    }

    @Override
    public boolean deleteVehicleCategory(int id) throws VehicleCategoryDetailsNotFoundException {
        VehicleCategory vehicleCategory = getVehicleCategoryDetails(id);
        vehicleCategoryDao.delete(vehicleCategory);
        return true;
    }

    @Override
    public List<VehicleCategory> getAllVehicleCategoryDetails() {
        return vehicleCategoryDao.findAll();
    }
}
