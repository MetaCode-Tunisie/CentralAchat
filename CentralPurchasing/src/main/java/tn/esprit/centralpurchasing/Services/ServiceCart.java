package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Entities.Cart;
import tn.esprit.centralpurchasing.Entities.Product;
import tn.esprit.centralpurchasing.Repository.AccountRepository;
import tn.esprit.centralpurchasing.Repository.CartRepository;
import tn.esprit.centralpurchasing.Repository.ProductRepository;

import java.util.List;
@Service
@AllArgsConstructor

public class ServiceCart  implements IServiceCart{
    CartRepository cartRepository;
    ProductRepository productRepository;
    AccountRepository accountRepository;
    @Override
    public Cart ajoutercart(Cart c,Long idProduct) {
        Product product = productRepository.findById(idProduct).orElse(null);

        return cartRepository.save(c);
    }

    @Override
    public Cart modefiercart(Long idcart, int quantity) {
        Cart c=cartRepository.findById(idcart).orElse(null);
        c.setQuantity(quantity);
        return cartRepository.save(c);
    }

    @Override
    public List<Cart> affichercart(Long idAccount) {


        return cartRepository.findByAccount_IdAccount(idAccount);
    }

    @Override
    public void supprimercart(Long id) {
    cartRepository.deleteById(id);
    }
    @Override
    public void supprimerallcart(Long idAccount) {

        List<Cart> carts= cartRepository.findByAccount_IdAccount(idAccount);
        for (Cart cart : carts) {
            cartRepository.deleteById(cart.getIdCart());

        }
    }
}
