package com.upgrad.mba.dto;

public class VehicleSubCategoryDTO {

        int vehicleSubCategoryId;
        String vehicleSubCategoryName;
        float Price;
        int cityId;

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

        public void setPrice(float ticketPrice) {
            this.Price = Price;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

}
