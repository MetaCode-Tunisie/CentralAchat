package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Currency;
import tn.esprit.centralpurchasing.Repository.CurrencyRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class ServiceCurrency implements IServiceCurrency {
    private CurrencyRepository currencyRepository;

    @Override
    public void ajouterCurrency(Currency currency) {
        currencyRepository.save(currency);
    }
    @Override
    public List<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency updateCurrency(Long idCurrency, Currency currency) {
        return currencyRepository.findById(idCurrency)
                .map(currency1  -> {
                    currency1.setName(currency.getName());
                    currency1.setRate(currency.getRate());


                    return currencyRepository.save(currency1);
                }).orElseThrow(() -> new RuntimeException("Currency non trouvé !"));
    }


}
