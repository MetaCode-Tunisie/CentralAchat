package tn.esprit.centralpurchasing.Controller;

import com.stripe.Stripe;

import com.stripe.exception.CardException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


}