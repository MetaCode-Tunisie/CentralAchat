package tn.esprit.centralpurchasing.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Currency;
import tn.esprit.centralpurchasing.Service.IServiceCurrency;

import java.util.List;

@RestController
public class CurrencyController {
    @Autowired
    IServiceCurrency ServiceCurrency;
    @PostMapping("/ajouterCurrency")
    public void ajouterCurrency(@RequestBody Currency currency){
        ServiceCurrency.ajouterCurrency(currency);
    }
    @GetMapping("/AfficherCurrency")
    public List<Currency> getAllCurrency() {
        return ServiceCurrency.getAllCurrency();
    }

   @PutMapping("/{idCurrency}")
    public Currency  updateCurrency(@PathVariable Long idCurrency, @RequestBody Currency currency) {
      return   ServiceCurrency.updateCurrency(idCurrency,currency);
    }


}
