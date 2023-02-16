package tn.esprit.centralpurchasing.Services;


import tn.esprit.centralpurchasing.Entities.Orders;

import java.util.List;

public interface IServiceOrder {
    Orders AddOrder (Orders o );
    Orders UpdateOrder (Orders o );
    void DeleteOrder(Long id );
    List<Orders> AfficherOrder ();

}
