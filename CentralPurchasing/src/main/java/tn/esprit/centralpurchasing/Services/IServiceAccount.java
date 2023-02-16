package tn.esprit.centralpurchasing.Services;

import tn.esprit.centralpurchasing.Entities.Account;

import java.util.List;

public interface IServiceAccount {

    // ********************** ADMIN *********************** //
    Account SignUpAdmin(Account account);
    Account updateAdmin(Account account);

    // ********************** OPERATOR *********************** //
    Account addOperator(Account account);
    Account updateOperator(Account account);

    // ********************** SUPPLIER *********************** //
    Account addSupplier(Account account);
    Account affectSupplierToOperator(Long idOperator,Long idSupplier);
    Account disaffectSupplierToOperator(Long idSupplier);





    // ********************** BUYER *********************** //
    Account SignUp(Account account);
    Account updateBuyer(Account account);
    Account disableBuyer(Long idBuyer);
    Account affectBuyerToSupplier(Long idSupplier,Long idBuyer);






    List<Account> getAll();

    Account loadUserByUsername(String username);
}
