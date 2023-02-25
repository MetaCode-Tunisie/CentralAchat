package tn.esprit.centralpurchasing.Services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import tn.esprit.centralpurchasing.Entities.Category;
import tn.esprit.centralpurchasing.Entities.Location;

import java.util.List;

public interface IServiceLocation {
    public Location AddLocation(Location location);
    public List<Location> displayLocation();
    public void updateLocation(Long idLocation, String Adress);
    void deleteLocation( Long idLocation);

}
