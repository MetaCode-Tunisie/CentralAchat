package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Location;
import tn.esprit.centralpurchasing.Repository.LocationRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceLocation implements IServiceLocation {
    LocationRepository locationRepository;
    @Override
    public Location AddLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public List<Location> displayLocation() {
        return locationRepository.findAll();
    }

    @Override
    public void updateLocation(Long idLocation, String Adress) {
Location location =locationRepository.findById(idLocation).get();
location.setAddress(Adress);
locationRepository.save(location);

    }

    @Override
    public void deleteLocation(Long idLocation) {
locationRepository.deleteById(idLocation);
    }

}
