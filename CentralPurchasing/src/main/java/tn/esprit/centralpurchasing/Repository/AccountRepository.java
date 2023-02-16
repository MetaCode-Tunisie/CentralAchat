package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Entities.TypeRole;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByEmail(String email);

    Account findByRolesTypeRole(TypeRole typeRole);

    Account findByIdAccountAndRolesTypeRole(Long idAccount,TypeRole typeRole);

}
