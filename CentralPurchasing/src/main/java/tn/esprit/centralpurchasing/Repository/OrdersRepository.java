package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.Orders;

public interface OrdersRepository extends JpaRepository<Orders,Long> {
}
