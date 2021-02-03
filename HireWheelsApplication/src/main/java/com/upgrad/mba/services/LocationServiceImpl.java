package com.upgrad.mba.services;

import com.upgrad.mba.dao.LocationDao;
import com.upgrad.mba.entities.Location;
import com.upgrad.mba.exceptions.LocationDetailsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDao locationDao;

    @Override
    public Location acceptLocationDetails(Location location) {
        return locationDao.save(location);
    }

    @Override
    public Location getLocationDetails(int id) throws LocationDetailsNotFoundException {
        return locationDao.findById(id)
                .orElseThrow(
                        () -> new LocationDetailsNotFoundException("Location not found by id: " + id)
                );
    }

    @Override
    public Location getLocationDetailsByLocationName(String locationName) throws LocationDetailsNotFoundException {
        return locationDao.findByLocationName(locationName)
                .orElseThrow(
                        () -> new LocationDetailsNotFoundException("Location not found by locationName: " + locationName)
                );
    }

    @Override
    public boolean deleteLocation(int id) throws LocationDetailsNotFoundException {
        Location location = getLocationDetails(id);
        locationDao.delete(location);
        return true;
    }

    @Override
    public List<Location> getAllLocationDetails() {
        return locationDao.findAll();
    }
}
