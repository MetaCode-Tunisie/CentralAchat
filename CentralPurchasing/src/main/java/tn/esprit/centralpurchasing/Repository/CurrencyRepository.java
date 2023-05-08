package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.Currency;

public interface CurrencyRepository extends JpaRepository<Currency,Long> {

}

