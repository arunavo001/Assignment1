package com.upgrad.mba.services;

import com.upgrad.mba.dao.VehicleSubCategoryDao;
import com.upgrad.mba.entities.VehicleSubCategory;
import com.upgrad.mba.exceptions.VehiclesSubCategoryDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleSubCategoryServiceImpl implements VehicleSubCategoryService {

    @Autowired
    private VehicleSubCategoryDao vehicleSubCategoryDao;

    @Override
    public VehicleSubCategory acceptVehicleSubCategoryDetails(VehicleSubCategory vehicleSubCategory) {
        return vehicleSubCategoryDao.save(vehicleSubCategory);
    }

    @Override
    public VehicleSubCategory getVehicleSubCategoryDetails(int id) throws VehiclesSubCategoryDetailsNotFoundException {
        return vehicleSubCategoryDao.findById(id)
                .orElseThrow(
                        () -> new VehiclesSubCategoryDetailsNotFoundException("VehicleSubCategory not found for id: " + id)
                );
    }

    @Override
    public VehicleSubCategory updateVehicleSubCategoryDetails(int id, VehicleSubCategory vehicleSubCategory) throws VehiclesSubCategoryDetailsNotFoundException {
        VehicleSubCategory savedVehicleSubCategory = getVehicleSubCategoryDetails(id);

        if (isNotNullOrZero(vehicleSubCategory.getVehicleSubCategoryName())) {
            savedVehicleSubCategory.setVehicleSubCategoryName(vehicleSubCategory.getVehicleSubCategoryName());
        }
        if (isNotNullOrZero(vehicleSubCategory.getPrice())) {
            savedVehicleSubCategory.setPrice(vehicleSubCategory.getPrice());
        }
        if (isNotNullOrZero(vehicleSubCategory.getCity())) {
            savedVehicleSubCategory.setCity(vehicleSubCategory.getCity());
        }

        return vehicleSubCategoryDao.save(vehicleSubCategory);
    }

    private boolean isNotNullOrZero(Object obj) {
        return obj != null;
    }

    private boolean isNotNullOrZero(int val) {
        return val != 0;
    }

    private boolean isNotNullOrZero(float val) {
        return val >= 0.1f;
    }

    @Override
    public boolean deleteVehicleSubCategory(int id) throws VehiclesSubCategoryDetailsNotFoundException {
        VehicleSubCategory savedVehicleSubCategory = getVehicleSubCategoryDetails(id);
        vehicleSubCategoryDao.delete(savedVehicleSubCategory);
        return true;
    }

    @Override
    public List<VehicleSubCategory> getAllVehicleSubCategoryDetails() {
        return vehicleSubCategoryDao.findAll();
    }
}
