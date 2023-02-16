package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery , Long> {
}
