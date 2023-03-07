package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Entities.Invoice;
import tn.esprit.centralpurchasing.Entities.Orders;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders , Long> {

    List<Orders> findByDeliveryIdDelivery(Long idDelivery);

    List<Orders> findByValidAndAccountsIdAccount(Boolean valid,Long idAccount);

    List<Orders>findByAccountsIdAccount(Long idAccount);

    List<Orders>findByRefAndValidAndAccountsIdAccount(String ref,Boolean valid,Long idAccount);

    List<Orders>findByRef(String ref);

    List<Orders>findByValid(Boolean valid);


    @Query("SELECT o.invoice from Orders o where o.idOrder=:idOrder")
    Invoice findInvoice(Long idOrder);

}
