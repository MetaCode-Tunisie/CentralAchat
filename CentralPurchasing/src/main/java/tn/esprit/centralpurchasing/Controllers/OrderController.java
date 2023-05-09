package tn.esprit.centralpurchasing.Controllers;

import com.stripe.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Orders;
import tn.esprit.centralpurchasing.Repository.OrdersRepository;
import tn.esprit.centralpurchasing.Services.IServiceOrder;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderController {
IServiceOrder iServiceOrder;
OrdersRepository ordersRepository;

    @GetMapping("Affichercart/{idAccount}")
    public List<Orders> Affichercart(@PathVariable Long idAccount ){
        return iServiceOrder.Affichercart(idAccount);}


    @DeleteMapping("DeleteCart/{idCart}")
    public void Deletecart(@PathVariable Long idCart){
         ordersRepository.deleteById(idCart);}

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

    @GetMapping("/getNoNPaiement/{idAccount}")
    public List<Orders> GetOrders(@PathVariable Long idAccount ){
        return  ordersRepository.retrieveOrders(idAccount);


    }

}
