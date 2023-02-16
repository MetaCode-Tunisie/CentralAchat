package tn.esprit.centralpurchasing.Services;

import tn.esprit.centralpurchasing.Entities.Cart;

import java.util.List;

public interface IServiceCart {
    Cart ajoutercart (Cart c,Long idProduct );
    public Cart modefiercart(Long idcart, int quantity) ;
    List<Cart> affichercart(Long idAccount) ;
    void supprimercart( Long id);
    void supprimerallcart(Long idAccount) ;
}
