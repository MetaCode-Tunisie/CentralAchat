package tn.esprit.centralpurchasing.Service;

import tn.esprit.centralpurchasing.Entities.Currency;

import java.util.List;

public interface IServiceCurrency {
     void ajouterCurrency(Currency currency);
    List<Currency> getAllCurrency();

     Currency updateCurrency(Long idCurrency,Currency currency);


}
