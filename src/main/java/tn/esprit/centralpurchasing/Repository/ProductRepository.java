package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
