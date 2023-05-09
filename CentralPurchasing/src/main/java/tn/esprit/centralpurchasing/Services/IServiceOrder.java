package tn.esprit.centralpurchasing.Services;


import tn.esprit.centralpurchasing.Entities.Orders;

import java.util.List;

public interface IServiceOrder {

    public Orders UpdateOrder( Long idOrder,int quantity);
    public void  ChekedValid(Long idAccount,Boolean delivery);
    public List<Orders> AfficherOrder(Long idAccount );
    public Orders AjouterCommecart(Long idProduct , int quantity, Long idAccount);
    public List<Orders> Affichercart(Long idAccount );

}
