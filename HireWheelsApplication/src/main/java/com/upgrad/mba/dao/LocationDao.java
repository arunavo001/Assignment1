package com.upgrad.mba.dao;

import com.upgrad.mba.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationDao extends JpaRepository<Location, Integer> {
    public Optional<Location> findByLocationName(String locationName);
}
