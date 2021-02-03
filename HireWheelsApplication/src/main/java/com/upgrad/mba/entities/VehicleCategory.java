package com.upgrad.mba.entities;

import javax.persistence.*;

@Entity
public class VehicleCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int vehicleCategoryId;

    @ManyToOne
    @JoinColumn(name = "vehicle_category_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "vehicle_subcategory_id", nullable = false)
    private VehicleSubCategory vehicleSubCategory;

    public int getVehicleCategoryId() {
        return vehicleCategoryId;
    }

    public void setVehicleCategoryId(int vehicleCategoryId) {
        this.vehicleCategoryId = vehicleCategoryId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public VehicleSubCategory getVehicleSubCategory() {
        return vehicleSubCategory;
    }

    public void setVehicleSubCategory(VehicleSubCategory vehicleSubCategory) {
        this.vehicleSubCategory = vehicleSubCategory;
    }

    @Override
    public String toString() {
        return "vehicleCategory{" +
                "vehicleCategoryId=" + vehicleSubCategory +
                ", vehicle=" + vehicle +
                ", vehicleSubCategory=" + vehicleSubCategory +
                '}';
    }
}
