package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.Reciept;

public interface RecieptRepository extends JpaRepository<Reciept , Long> {

    Reciept findByDeliveryIdDelivery(Long idDelivery);
}
