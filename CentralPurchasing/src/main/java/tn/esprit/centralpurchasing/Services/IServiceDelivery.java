package tn.esprit.centralpurchasing.Services;

import tn.esprit.centralpurchasing.Entities.Delivery;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface IServiceDelivery {


    public List<Delivery> getAllDeliveries();

    public Delivery searchDelivery(Long id);


   public Map<String, Boolean> suivie(Long idDelivery, Long idAccount);
    public void  add(String destinationAddress,Long idAccount);



    }
