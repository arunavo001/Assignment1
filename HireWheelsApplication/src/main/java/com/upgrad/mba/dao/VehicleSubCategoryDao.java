package com.upgrad.mba.dao;

import com.upgrad.mba.entities.VehicleSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleSubCategoryDao extends JpaRepository<VehicleSubCategory, Integer> {
    public List<VehicleSubCategory> findByVehicleSubCategoryName(String vehicleSubCategoryName);
    public List<VehicleSubCategory> findByPriceLessThan(float price);
    public List<VehicleSubCategory> findByVehicleSubCategoryNameContaining(String vehicleSubCategoryName);
}
