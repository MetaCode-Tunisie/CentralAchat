package tn.esprit.centralpurchasing.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Cart;
import tn.esprit.centralpurchasing.Repository.CartRepository;
import tn.esprit.centralpurchasing.Services.IServiceCart;

import java.util.List;

@RestController
@AllArgsConstructor
public class CartController {
IServiceCart iServiceCart;
    private final CartRepository cartRepository;

    @PostMapping("/ajoutercart/{idProduct}")
    public Cart ajoutercart(@RequestBody Cart c,@PathVariable long idProduct) {
        return iServiceCart.ajoutercart(c,idProduct);
    }
    @PutMapping("/modefiercart/{idcart}/{quantity}")
    public Cart modefiercart( @PathVariable long idcart,@PathVariable int quantity) {
        return iServiceCart.modefiercart(idcart,quantity);
    }
    @GetMapping("/affichercart/{idAccount}")
    List<Cart> affichercart(@PathVariable long idAccount) {
        return  iServiceCart.affichercart(idAccount);
    }


    @DeleteMapping("/supprimercart/{id}")
    void supprimercart(@PathVariable Long id) {
        iServiceCart.supprimercart(id);
    }

}
