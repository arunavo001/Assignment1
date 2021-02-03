package com.upgrad.mba.services;

import com.upgrad.mba.dao.*;
import com.upgrad.mba.entities.*;
import com.upgrad.mba.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service("InitService")
public class InitServiceImpl implements InitService{
    @Autowired
    @Qualifier("cityDao")
    CityDao cityDao;

    @Autowired
    @Qualifier("roleDao")
    RoleDao roleDao;

    @Autowired
    @Qualifier("locationDao")
    LocationDao locationDao;

    @Autowired
    @Qualifier("statusDao")
    StatusDao statusDao;

    @Autowired
    CustomerService customerService;

    @Autowired
    VehicleSubCategoryService vehicleSubCategoryService;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    VehicleCategoryService vehicleCategoryService;

    @Autowired
    BookingService bookingService;

    List<City> cities = Arrays.asList(new City("Patna"), new City("Mumbai"), new City("Kolkata"), new City("Bangalore"));
    List<Role> roles = Arrays.asList(new Role("Customer"), new Role("Admin"));
    List<Location> locations = Arrays.asList(new Location("India"), new Location("America"), new Location("China"));
    List<Status> statuses = Arrays.asList(new Status("Upcoming"), new Status("In-Process"), new Status("Blocked"));

    Customer customer = new Customer();
    Vehicle vehicleSubCategory1 = new Vehicle();
    Vehicle vehicleSubCategory2 = new Vehicle();
    Vehicle vehicle1 = new Vehicle();
    Vehicle vehicle2 = new Vehicle();
    VehicleCategory vehicleCategory1 = new VehicleCategory();

    public void addCustomer() throws CustomerUserNameExistsException, RoleTypeDetailsNotFoundException {
        customer.setFirstName("Emma");
        customer.setLastName("Stone");
        customer.setUsername("emma123stone");
        customer.setPassword("emma@amme");
        customer.setDateOfBirth(LocalDateTime.of(1988, 11, 6, 0, 0));
        customer.setUserType(roles.get(0));
        customer.setLanguage(locations.get(0));
        customer = customerService.acceptCustomerDetails(customer);
    }

    public void addVehicleSubCategory(){
        vehicleSubCategory1.setVehicleSubCategoryName("Category M");
        vehicleSubCategory1.setPrice(5000);
        vehicleSubCategory1.setCity(cities.get(0));
        vehicleSubCategory1 = vehicleService.acceptVehicleDetails(vehicleSubCategory1);

        vehicleSubCategory2.setVehicleSubCategoryName("CategoryL");
        vehicleSubCategory2.setPrice(6000);
        vehicleSubCategory2.setCity(cities.get(1));
        vehicleSubCategory2 = vehicleService.acceptVehicleDetails(vehicleSubCategory2);
    }

    public void addVehicle(){
        vehicle1.setVehicleName("Maroti");
        vehicle1.setVehicleDescription("Maruti Suzuki India Limited, formerly known as Maruti Udyog Limited, is an Indian automobile manufacturer company, founded by Government of India in 1981.");
        vehicle1.setReleaseDate(LocalDateTime.of(2018, 4, 27, 5, 30));
        vehicle1.setDuration(150);
        vehicle1.setCoverPhotoUrl("cover-photo-url");
        vehicle1.setTrailerUrl("trial-url");
        vehicle1.setStatus(statuses.get(0));
        vehicle1 = vehicleService.acceptVehicleDetails(vehicle1);

        vehicle2.setVehicleName("Yamaha");
        vehicle2.setVehicleDescription("Yamaha Motor Co., Ltd. is a Japanese manufacturer of motorcycles, marine products such as boats and outboard motors, and other motorized products.");
        vehicle2.setReleaseDate(LocalDateTime.of(2019, 4, 26, 5, 30));
        vehicle2.setDuration(180);
        vehicle2.setCoverPhotoUrl("cover-photo-url");
        vehicle2.setTrailerUrl("trial-url");
        vehicle2.setStatus(statuses.get(1));
        vehicle2 = vehicleService.acceptVehicleDetails(vehicle2);
    }

    private void addVehicleCategory() throws VehiclesSubCategoryDetailsNotFoundException, VehicleDetailsNotFoundException {

        vehicleSubCategory1.setVehicle(vehicle1);
        vehicleSubCategory1.setVehicle(vehicle1);
    }

    private void addBooking() throws CustomerDetailsNotFoundException, VehicleCategoryDetailsNotFoundException {
        Booking booking = new Booking();
        booking.setBookingDate(LocalDateTime.of(2019, 1, 8, 0, 10));
        booking.setCustomer(customer);
        booking.setNoOfSeats(4);
        booking.setVehicleCategory(vehicleCategory1);
        bookingService.acceptBookingDetails(booking);
    }

    @Override
    public void init() throws CustomerUserNameExistsException, RoleTypeDetailsNotFoundException, VehiclesSubCategoryDetailsNotFoundException, VehicleDetailsNotFoundException, VehicleCategoryDetailsNotFoundException, CustomerDetailsNotFoundException {
        cities.forEach(city -> cityDao.save(city));
        roles.forEach(role -> roleDao.save(role));
        locations.forEach(location -> locationDao.save(location));
        statuses.forEach(status -> statusDao.save(status));
        addCustomer();
        addVehicleSubCategory();
        addVehicle();
        addVehicleCategory();
        addBooking();
    }

}
