package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Dto.DeliveryDto;
import tn.esprit.centralpurchasing.Entities.Delivery;
import tn.esprit.centralpurchasing.Services.IServiceDelivery;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
public class DeliveryController {
    private IServiceDelivery iServiceDelivery;

    /*@PostMapping("/addDelivery")
    public Delivery addDelivery(Long idOrders, String destinationAddress){
        return  iServiceDelivery.addDelivery(idOrders, destinationAddress);
    }*/

    // http://localhost:8585/getAllDeliveries
    @GetMapping("/getAllDeliveries")
    public List<Delivery> getAllDeliveries(){
        return iServiceDelivery.getAllDeliveries();
    }

    // http://localhost:8585/searchDelivery
    @GetMapping("/searchDelivery/{id}")
    public Delivery searchDelivery(@PathVariable Long id){
        return iServiceDelivery.searchDelivery(id);
    }

   /* @PostMapping("/affectRecieptToDelivery/{idReciept}/{idDelivery}")
    public void affectRecieptToDelivery(@PathVariable Long idReciept,@PathVariable Long idDelivery){
        iServiceDelivery.affectRecieptToDelivery(idReciept,idDelivery);
    }*/

    // http://localhost:8585/add
    @PostMapping("add/{destinationAddress}/{idAccount}")
    public void  add(@PathVariable String destinationAddress,@PathVariable Long idAccount){
        iServiceDelivery.add(destinationAddress,idAccount);
    }

    // http://localhost:8585/suivie
        @GetMapping("/suivie/{idDelivery}/{idAccount}")
        public Integer suivie(@PathVariable Long idDelivery,@PathVariable Long idAccount){
        return iServiceDelivery.suivie(idDelivery, idAccount);
    }


    // http://localhost:8585/findDeliveriesByUser
    @GetMapping("findDeliveriesByUser/{idAccount}")
    public Set<DeliveryDto> findDeleveriesByUser(@PathVariable Long idAccount){
        return iServiceDelivery.findDeleveriesByUser(idAccount);
    }
}
