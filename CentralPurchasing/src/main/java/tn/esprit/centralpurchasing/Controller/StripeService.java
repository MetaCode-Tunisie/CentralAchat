package tn.esprit.centralpurchasing.Controller;

import com.stripe.Stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Invoice;
import tn.esprit.centralpurchasing.Entities.Orders;
import tn.esprit.centralpurchasing.Repository.OrdersRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripeService {

    @Autowired
    OrdersRepository ordersRepository;

    public StripeService(@Value("${stripe.secretKey}") String apiKey) {
        Stripe.apiKey = apiKey;
    }

    public String  charge(String ref, String currency) throws StripeException {
        List<Orders> orders = ordersRepository.findByRef(ref);
        Invoice invoice = ordersRepository.findInvoice(orders.get(0).getIdOrder());
        Map<String, Object> params = new HashMap<>();
        params.put("amount",Math.round(invoice.getTotalprice()) * 100L);
        params.put("currency", currency);
        params.put("source", "tok_visa");
        Charge charge = Charge.create(params);
        if(charge.getStatus().equals("succeeded")){
            for (Orders o : orders){
                o.setPayment(true);
                ordersRepository.save(o);
            }
        }
        return "Paiement Succed !!";
    }
}