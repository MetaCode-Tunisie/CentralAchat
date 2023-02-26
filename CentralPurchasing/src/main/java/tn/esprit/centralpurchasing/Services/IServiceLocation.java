package tn.esprit.centralpurchasing.Services;

import tn.esprit.centralpurchasing.Entities.Location;

import java.util.List;

public interface IServiceLocation {
    public Location AddLocation(Location location);
    public List<Location> displayLocation();
    public void updateLocation(Long idLocation, String Adress);
    void deleteLocation( Long idLocation);

}
