package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.Orders;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders , Long> {

    Orders findByDeliveryIdDelivery(Long idDelivery);
}
