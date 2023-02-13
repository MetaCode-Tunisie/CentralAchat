package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByEmail(String email);

}
