package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Cart;
import tn.esprit.centralpurchasing.Entities.Delivery;
import tn.esprit.centralpurchasing.Entities.Orders;
import tn.esprit.centralpurchasing.Entities.Reciept;
import tn.esprit.centralpurchasing.Repository.DeliveryRepository;
import tn.esprit.centralpurchasing.Repository.OrdersRepository;
import tn.esprit.centralpurchasing.Repository.RecieptRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ServiceDelivery implements IServiceDelivery{
    private DeliveryRepository deliveryRepository;
    private RecieptRepository recieptRepository;
    private OrdersRepository ordersRepository;
    @Override
    public Delivery addDelivery(Long idOrders, String destinationAddress) {
        Orders orders = ordersRepository.findById(idOrders).orElse(null);
        Delivery delivery = new Delivery();
      Cart cart = orders.getCarts().stream().findFirst().orElse(null);
            delivery.setDepartureAddress(cart.getProduct().getLocation().getAddress());
        //delivery.setDepartureAddress("tunis");
        delivery.setDestinationAddress(destinationAddress);
        delivery.setOrders(orders);
        return deliveryRepository.save(delivery);
        }




    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery searchDelivery(Long id) {
        return deliveryRepository.findById(id).orElse(null);
    }

   /* @Override
    public void affectRecieptToDelivery(Long idReciept, Long idDelivery) {

        Reciept reciept = recieptRepository.findById(idReciept).orElse(null);
        Delivery delivery = deliveryRepository.findById(idDelivery).orElse(null);
        delivery.setReciept(reciept);
        deliveryRepository.save(delivery);
    }*/

    /*@Override
    public List<Integer> suivie(Long idDelivery) {
        List<Integer> etat = new ArrayList<>();
        Orders orders = ordersRepository.findByDeliveryIdDelivery(idDelivery);
        if(orders.getPayment()== true){
            etat.add(1);

            if(recieptRepository.findByDeliveryIdDelivery(idDelivery).getStatus()==true){
                etat.add(1);
            }
        }
        return etat;
    }*/
}
