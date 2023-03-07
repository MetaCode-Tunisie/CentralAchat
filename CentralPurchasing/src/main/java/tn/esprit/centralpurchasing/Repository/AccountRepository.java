package tn.esprit.centralpurchasing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.centralpurchasing.Entities.Account;

import tn.esprit.centralpurchasing.Entities.TypeRole;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByEmail(String email);

    Account findByEmailAndCodeTel(String email,String codetel);
    Account findByPhoneNumberAndCodeTel(String numTel,String codetel);

    Account findByResetToken(String resetToken);
    Account findByPhoneNumber(String phoneNumber);
    Account findByIdAccountAndRolesTypeRole(Long idAccount,TypeRole typeRole);

}
