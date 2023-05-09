package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {
}
