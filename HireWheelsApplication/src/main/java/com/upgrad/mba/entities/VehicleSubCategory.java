package com.upgrad.mba.entities;

import javax.persistence.*;

@Entity
public class VehicleSubCategory {
    @Id
    @GeneratedValue
    private int vehicleSubCategoryId;

    @Column(length = 20, nullable = false, unique = true)
    private String vehicleSubCategoryName;

    @Column(nullable = false)
    private float Price = 150.00f;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public int getVehicleSubCategoryId() {
        return vehicleSubCategoryId;
    }

    public void setVehicleSubCategoryId(int vehicleSubCategoryId) {
        this.vehicleSubCategoryId = vehicleSubCategoryId;
    }

    public String getVehicleSubCategoryName() {
        return vehicleSubCategoryName;
    }

    public void setVehicleSubCategoryName(String vehicleSubCategoryName) {
        this.vehicleSubCategoryName = vehicleSubCategoryName;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "VehicleSubCategory{" +
                "vehicleSubCategoryId=" + vehicleSubCategoryId +
                ", vehicleSubCategoryName='" + vehicleSubCategoryName + '\'' +
                ", Price=" + Price +
                ", city=" + city +
                '}';
    }

    public void setVehicle(Vehicle vehicle1) {
    }
}
