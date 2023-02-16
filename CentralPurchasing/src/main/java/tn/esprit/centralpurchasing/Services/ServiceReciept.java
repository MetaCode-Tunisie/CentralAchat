package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Delivery;
import tn.esprit.centralpurchasing.Entities.Reciept;
import tn.esprit.centralpurchasing.Repository.DeliveryRepository;
import tn.esprit.centralpurchasing.Repository.RecieptRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceReciept implements IServiceReciept{
    private RecieptRepository recieptRepository;
    private DeliveryRepository deliveryRepository;
    @Override
    public Reciept addReciept(Reciept reciept, Long idDelivery) {
        /*Delivery delivery = deliveryRepository.findById(idDelivery).orElse(null);
        delivery.setReciept(reciept);
        return recieptRepository.save(reciept);*/

        Delivery delivery = deliveryRepository.findById(idDelivery).orElse(null);
        reciept.setDelivery(delivery);
        return recieptRepository.save(reciept);

    }

    @Override
    public List<Reciept> getAllReciepts() {
        return recieptRepository.findAll();
    }

    @Override
    public Reciept searchReciept(Long idReciept) {
        return recieptRepository.findById(idReciept).orElse(null);
    }
}
