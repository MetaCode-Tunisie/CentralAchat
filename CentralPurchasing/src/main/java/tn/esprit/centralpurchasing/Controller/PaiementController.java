package tn.esprit.centralpurchasing.Controller;
import com.stripe.Stripe;
import com.stripe.exception.CardException;
import com.stripe.exception.StripeException;

import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Invoice;
import tn.esprit.centralpurchasing.Entities.Orders;
import tn.esprit.centralpurchasing.Repository.OrdersRepository;
import tn.esprit.centralpurchasing.Services.EmailSenderService;
import tn.esprit.centralpurchasing.Services.PDFGeneratorService;
import tn.esprit.centralpurchasing.Services.ServiceInvoice;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ServiceInvoice serviceInvoice;

    @Autowired
    PDFGeneratorService pdfGeneratorService;

    @Autowired
    private EmailSenderService Emailservice;

    public PaiementController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/charge/{idAccount}/{totale}")
    public String charge(HttpServletResponse response,@RequestBody String token, @PathVariable Long idAccount , @PathVariable float totale) throws StripeException, IOException, TransformerException, MessagingException {
        Stripe.apiKey = "sk_test_51MfrX9LrRzJlmdztdn7bqmARXfhnETCT2Ili9N4oF7UNAmZsaoP1B5qFisPSzxerJjlytgQdIIDAwtAYFar5MW7500qTM2DnJd";
        Map<String, Object> params = new HashMap<>();
        params.put("amount", Math.round(totale) * 100); // amount in cents, adjust as needed
        params.put("currency", "usd");
        params.put("description", "Product Payment");
        params.put("source", token);

        try {
            Charge charge = Charge.create(params);
            List<Orders> orders1 = ordersRepository.retrieveOrders(idAccount);

            if(charge.getStatus().equals("succeeded")){

                for (Orders o : orders1){
                    o.setPayment(true);
                    ordersRepository.save(o);
                }
                serviceInvoice.addInvoice(orders1.get(0).getRef(),idAccount);
                response.setContentType("application/pdf");
                DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
                String currentDateTime = dateFormatter.format(new Date());

                String headerKey = "Content-Disposition";
                String headerValue = "attachment; filename=FACTURE_" + currentDateTime + ".pdf";
                response.setHeader(headerKey, headerValue);
                Orders order = this.pdfGeneratorService.export(response,orders1.get(0).getRef());
                Emailservice.sendEmailWithAttachment(order.getAccounts().getEmail(),"This is your invoice","Invoice");

            }
            return orders1.get(0).getRef();
        } catch (CardException e) {
            return "n'est pas Success";
        }
    }


}
