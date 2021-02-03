package com.upgrad.mba.services;

import com.upgrad.mba.entities.Location;
import com.upgrad.mba.exceptions.LocationDetailsNotFoundException;

import java.util.List;

public interface LocationService {
    public Location acceptLocationDetails(Location location);
    public Location getLocationDetails(int id) throws LocationDetailsNotFoundException;
    public Location getLocationDetailsByLocationName(String locationName) throws LocationDetailsNotFoundException;
    public boolean deleteLocation(int id) throws LocationDetailsNotFoundException;
    public List<Location> getAllLocationDetails();
}
