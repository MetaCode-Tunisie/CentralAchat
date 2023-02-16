package tn.esprit.centralpurchasing.Services;

import tn.esprit.centralpurchasing.Entities.Reciept;

import java.util.List;

public interface IServiceReciept {

    public Reciept addReciept(Reciept reciept, Long idDelivery);

    public List<Reciept> getAllReciepts();

    public Reciept searchReciept(Long idReciept);
}
