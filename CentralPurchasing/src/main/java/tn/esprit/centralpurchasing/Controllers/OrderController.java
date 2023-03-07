package tn.esprit.centralpurchasing.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Orders;
import tn.esprit.centralpurchasing.Services.IServiceOrder;

import java.util.List;

@AllArgsConstructor
@RestController
public class OrderController {
IServiceOrder iServiceOrder;

    @GetMapping("Affichercart/{idAccount}")
    public List<Orders> Affichercart(@PathVariable Long idAccount ){
        return iServiceOrder.Affichercart(idAccount);}


    @GetMapping("AfficherOrder/{idAccount}")
    public List<Orders> AfficherOrder(@PathVariable Long idAccount ){
        return iServiceOrder.AfficherOrder(idAccount);
    }
    @PutMapping("UpdateOrder/{idOrder}/{quantity}")
    public Orders UpdateOrder (@PathVariable Long idOrder ,@PathVariable int quantity ) {
    return  iServiceOrder.UpdateOrder(idOrder,quantity);
    }

   @PostMapping("Ajoutercart/{idProduct}/{quantity}/{idAccount}")
    public Orders AjouterCommecart(@PathVariable Long idProduct ,@PathVariable int quantity,@PathVariable Long idAccount){
        return iServiceOrder.AjouterCommecart(idProduct,quantity,idAccount);
    }
    @PutMapping("ChekedValid/{idAccount}/{delivery}")
    public void  ChekedValid(@PathVariable Long idAccount,@PathVariable Boolean delivery){
        iServiceOrder.ChekedValid(idAccount,delivery);


    }
}
