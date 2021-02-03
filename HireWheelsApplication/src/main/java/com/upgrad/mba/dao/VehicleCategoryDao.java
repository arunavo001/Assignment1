package com.upgrad.mba.dao;

import com.upgrad.mba.entities.VehicleCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleCategoryDao extends JpaRepository<VehicleCategory, Integer> {
}
