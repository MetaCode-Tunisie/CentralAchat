package tn.esprit.centralpurchasing.Repository;

import ch.qos.logback.core.status.Status;
import org.apache.catalina.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Entities.Category;
import tn.esprit.centralpurchasing.Entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBynameContainingAndAdressContainingAndPriceBetween(
            String name, String adress, double minPrice, double maxPrice);

    @Query("SELECT p.status, COUNT(p) FROM Product p GROUP BY p.status")
    List<Object[]> countProductsByStatus();


}

