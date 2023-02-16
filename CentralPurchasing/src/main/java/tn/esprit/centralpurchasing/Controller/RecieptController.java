package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Reciept;
import tn.esprit.centralpurchasing.Services.IServiceReciept;

import java.util.List;

@RestController
@AllArgsConstructor
public class RecieptController {
    private IServiceReciept iServiceReciept;

    @PostMapping("/addReciept/{idDelivery}")
    public Reciept addReciept(Reciept reciept, @PathVariable Long idDelivery){
        return iServiceReciept.addReciept(reciept,idDelivery);
    }

    @GetMapping("/getAllReciepts")
    public List<Reciept> getAllReciepts(){
        return  iServiceReciept.getAllReciepts();
    }

    @GetMapping("/searchReciept/{idReciept}")
    public Reciept searchReciept(@PathVariable Long idReciept){
        return iServiceReciept.searchReciept(idReciept);
    }
}
