package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
