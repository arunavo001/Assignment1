package com.upgrad.mba.dao;

import com.upgrad.mba.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VehicleDao extends JpaRepository<Vehicle, Integer> {
    public List<Vehicle> findByVehicleName(String vehicleName);
    public List<Vehicle> findByVehicleNameAndDuration(String name, int duration);
    public List<Vehicle> findByReleaseDateBetween(LocalDateTime start, LocalDateTime end);
    public List<Vehicle> findByDurationGreaterThanEqual(int duration);
    public List<Vehicle> findByReleaseDateAfter(LocalDateTime releaseDate);
    public List<Vehicle> findByVehicleNameContaining(String vehicleName);
    public List<Vehicle> findByVehicleNameIgnoreCase(String vehicleName);
}
