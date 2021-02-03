package com.upgrad.mba.services;

import com.upgrad.mba.dao.LocationDao;
import com.upgrad.mba.entities.Location;
import com.upgrad.mba.exceptions.LocationDetailsNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class LocationServiceTest {

    @Mock
    private LocationDao locationDao;

    @InjectMocks
    private LocationServiceImpl locationService;

    @BeforeEach
    public void setupMockito() {
        Mockito.when(locationDao.save(new Location("save test"))).thenReturn(new Location(1, "save test"));

        Mockito.when(locationDao.findById(1)).thenReturn(Optional.of(new Location(1, "save test")));

        Mockito.when(locationDao.findByLocationName("save test")).thenReturn(Optional.of(new Location(1, "save test")));
    }

    @Test
    public void testAcceptLocationDetails() {
        Location location = new Location();
        location.setLocationName("save test");
        Location savedLocation = locationService.acceptLocationDetails(location);

        Assertions.assertNotNull(savedLocation);
        Assertions.assertTrue(savedLocation.getLocationId() != 0);
        Assertions.assertEquals("save test", savedLocation.getLocationName());
    }

    @Test
    public void testGetLocationDetails() throws LocationDetailsNotFoundException {
        Location location = new Location();
        location.setLocationName("save test");
        location = locationService.acceptLocationDetails(location);

        Location savedLocation = locationService.getLocationDetails(location.getLocationId());
        Assertions.assertNotNull(savedLocation);
        Assertions.assertTrue(savedLocation.getLocationId() != 0);
        Assertions.assertEquals("save test", savedLocation.getLocationName());
    }

    @Test
    public void testGetLocationDetailsByLocationName() throws LocationDetailsNotFoundException {
        Location location = new Location();
        location.setLocationName("save test");
        locationService.acceptLocationDetails(location);

        Location savedLocation = locationService.getLocationDetailsByLocationName("save test");
        Assertions.assertNotNull(savedLocation);
        Assertions.assertTrue(savedLocation.getLocationId() != 0);
        Assertions.assertEquals("save test", savedLocation.getLocationName());
    }
}
