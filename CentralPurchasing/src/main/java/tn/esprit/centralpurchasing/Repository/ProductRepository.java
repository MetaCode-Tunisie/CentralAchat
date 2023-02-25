package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Entities.Category;
import tn.esprit.centralpurchasing.Entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll(Specification<Product> specification);

    default Specification<Product> categoryEquals(Long idCategory) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category").get("idCategory"), idCategory);
    }

    default Specification<Product> priceGreaterThan(BigDecimal price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("price"), price);
    }

    default List<Product> findByCategoryAndPrice(Long idCategory, BigDecimal price) {
        Specification<Product> specification = Specification.where(categoryEquals(idCategory)).and(priceGreaterThan(price));
        return findAll(specification);


    }
}