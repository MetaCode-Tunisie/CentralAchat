package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
