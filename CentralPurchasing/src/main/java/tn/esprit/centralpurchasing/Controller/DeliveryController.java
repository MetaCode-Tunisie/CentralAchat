package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Delivery;
import tn.esprit.centralpurchasing.Services.IServiceDelivery;

import java.util.List;

@RestController
@AllArgsConstructor
public class DeliveryController {
    private IServiceDelivery iServiceDelivery;

    @PostMapping("/addDelivery")
    public Delivery addDelivery(Long idOrders, String destinationAddress){
        return  iServiceDelivery.addDelivery(idOrders, destinationAddress);
    }

    @GetMapping("/getAllDeliveries")
    public List<Delivery> getAllDeliveries(){
        return iServiceDelivery.getAllDeliveries();
    }

    @GetMapping("/searchDelivery/{id}")
    public Delivery searchDelivery(@PathVariable Long id){
        return iServiceDelivery.searchDelivery(id);
    }

   /* @PostMapping("/affectRecieptToDelivery/{idReciept}/{idDelivery}")
    public void affectRecieptToDelivery(@PathVariable Long idReciept,@PathVariable Long idDelivery){
        iServiceDelivery.affectRecieptToDelivery(idReciept,idDelivery);
    }*/
}
