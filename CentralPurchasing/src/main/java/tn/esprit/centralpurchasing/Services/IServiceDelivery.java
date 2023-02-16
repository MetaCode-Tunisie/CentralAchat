package tn.esprit.centralpurchasing.Services;

import tn.esprit.centralpurchasing.Entities.Delivery;

import java.util.List;

public interface IServiceDelivery {

    public Delivery addDelivery(Long idOrders, String destinationAddress);

    public List<Delivery> getAllDeliveries();

    public Delivery searchDelivery(Long id);

   // public void affectRecieptToDelivery(Long idReciept, Long idDelivery);

  //  public List<String> suivie(Long idDelivery);


}
