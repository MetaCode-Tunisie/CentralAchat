package tn.esprit.centralpurchasing.Services;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Config.TwilioConfig;
import tn.esprit.centralpurchasing.Entities.*;
import tn.esprit.centralpurchasing.Repository.AccountRepository;
import tn.esprit.centralpurchasing.Repository.DeliveryRepository;
import tn.esprit.centralpurchasing.Repository.OrdersRepository;
import tn.esprit.centralpurchasing.Repository.RecieptRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class ServiceDelivery implements IServiceDelivery{
    private DeliveryRepository deliveryRepository;
    private RecieptRepository recieptRepository;
    private OrdersRepository ordersRepository;
    private AccountRepository accountRepository;
    private TwilioConfig twilioConfig;



     @Override
    public void add(String destinationAddress, Long idAccount) {

         List<Orders> orders=ordersRepository.findByValidAndAccountsIdAccount(true,idAccount);

         for (Orders order : orders){

             if(order.getDelivery()==null && order.getDeliveryOption()==true && order.getPayment()==true) {
                 Delivery delivery = new Delivery();
                 delivery.setDestinationAddress(destinationAddress);
                 delivery.setDepartureAddress(order.getProduct().getLocation().getAddress());
                 for (Orders ordersCheck : orders) {
                     if (ordersCheck.getDelivery()==null){
                         if(order.getProduct().getLocation().equals(ordersCheck.getProduct().getLocation()))
                         {
                             ordersCheck.setDelivery(delivery);
                         }
                     }
                 }
                 deliveryRepository.save(delivery);
             }
         }

    }




    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public Delivery searchDelivery(Long id) {
        return deliveryRepository.findById(id).orElse(null);
    }


    @Override
    public Map<String, Boolean> suivie(Long idDelivery, Long idAccount) {
        Map<String, Boolean> suivie = new HashMap<>();
        Delivery delivery = deliveryRepository.findById(idDelivery).orElse(null);
        Account account = accountRepository.findById(idAccount).orElse(null);
        PhoneNumber to = new PhoneNumber(account.getPhoneNumber());
        PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
        String m = "Dear M/Ms "+account.getLastname()+",we have sent you this message to inform you that your order has been delivered thank you for your loyalty";
        List<Orders> orders = ordersRepository.findByDeliveryIdDelivery(idDelivery);
        if(orders.get(0).getDeliveryOption()==true){
            suivie.put("picked" , true);
            if(orders.get(0).getPayment()==true){
                suivie.put("assort",true);
                if(delivered(orders.get(0).getIdOrder())){
                    suivie.put("delivered" , true);
                   // Message message = Message
                     //       .creator(to,from,m).create();
                    Reciept reciept=recieptRepository.findByDeliveryIdDelivery(idDelivery);
                    if(reciept!=null ){
                        if(reciept.getStatus()==true)
                        suivie.put("completed",true);
                    }
                }

            }
        }


        return suivie;
    }

    public Boolean delivered(Long idOrder){
        Boolean status = false;
        Orders orders = ordersRepository.findById(idOrder).orElse(null);
        if(orders.getDelivery() != null)
            status = true;
        return status;
    }



}
