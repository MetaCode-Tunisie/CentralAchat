package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.centralpurchasing.Entities.Category;
import tn.esprit.centralpurchasing.Entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBynameContainingAndAdressContainingAndPriceBetween(
            String name, String adress, double minPrice, double maxPrice);

    @Query("SELECT p.status, COUNT(p) FROM Product p GROUP BY p.status")
    List<Object[]> countProductsByStatus();

    @Query("SELECT p FROM Product p where p.idProduct=:idp")
     Product retriveProduct(Long idp);

    List<Product> findByCategory(Category category);
    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.idProduct <> :productId")
    List<Product> findSimilarProducts(@Param("category") Category category, @Param("productId") Long productId);
}

