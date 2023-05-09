package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Location;
import tn.esprit.centralpurchasing.Services.IServiceLocation;

import java.util.List;

@RestController
@AllArgsConstructor
public class LocationController {
    IServiceLocation iServiceLocation;
@PostMapping("/AddLocation")
    public Location ajouterLocation(@RequestBody Location location){
return iServiceLocation.AddLocation(location);
    }
    @GetMapping("/ListLocation")
    public List<Location> displayLocation(){
return iServiceLocation.displayLocation();
    }
    @PutMapping("/updateLocation/{idLocation}/{Adress}")
    public void updateLocation(@PathVariable Long idLocation,@PathVariable String Adress){
     iServiceLocation.updateLocation(idLocation,Adress);
    }
    @DeleteMapping("/deleteLocation/{idLocation}")
    public void deleteLocation(Long idLocation){
    iServiceLocation.deleteLocation(idLocation);

    }


}
