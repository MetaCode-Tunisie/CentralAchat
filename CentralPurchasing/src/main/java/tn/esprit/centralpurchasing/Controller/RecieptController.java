package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Reciept;
import tn.esprit.centralpurchasing.Services.IServiceReciept;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
public class RecieptController {
    private IServiceReciept iServiceReciept;

    // http://localhost:8585/addReciept
    @GetMapping ("/addReciept/{idDelivery}")
    public void addReciept(@PathVariable Long idDelivery){
         iServiceReciept.addReciept(idDelivery);
    }

    // http://localhost:8585/getAllReciepts
    @GetMapping("/getAllReciepts")
    public List<Reciept> getAllReciepts(){
        return  iServiceReciept.getAllReciepts();
    }

    // http://localhost:8585/searchReciept
    @GetMapping("/searchReciept/{idReciept}")
    public Reciept searchReciept(@PathVariable Long idReciept){
        return iServiceReciept.searchReciept(idReciept);
    }
}
