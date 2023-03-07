package tn.esprit.centralpurchasing.Controller;
import com.stripe.exception.StripeException;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaiementController {




   /* @PostMapping("/create-payment-intent/{Montant}")
    public CreatePaymentResponse CreatePaiementIntent(@PathVariable Long Montant) throws StripeException {

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                            .setAmount(Montant * 100L)
                            .setCustomer("hazembayoudh886@gmail.com")
                            .setCurrency("usd")
                            .build();
                   PaymentIntent  paymentIntent = PaymentIntent.create(params);

            return new CreatePaymentResponse(paymentIntent.getClientSecret());
    }*/

    private StripeService stripeService;

    public PaiementController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/Paiement")
    public String charge(@RequestParam  String ref, @RequestParam String currency) throws StripeException {
        return stripeService.charge(ref, currency);
    }


}
