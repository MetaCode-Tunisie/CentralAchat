package tn.esprit.centralpurchasing.Services;

import tn.esprit.centralpurchasing.Dto.UserDto;
import tn.esprit.centralpurchasing.Entities.Account;
import java.util.List;

public interface IServiceAccount  {

    // ********************** ADMIN *********************** //
    Account SignUpAdmin(Account account);
    Account updateAdmin(Account account);

    // ********************** OPERATOR *********************** //
    Account addOperator(Account account);
    Boolean updateOperator(UserDto account);

    // ********************** SUPPLIER *********************** //
    Account addSupplier(Account account);
    Boolean updateSupplier(UserDto userDto);
    Account affectSupplierToOperator(Long idOperator,Long idSupplier);
    Boolean disaffectSupplierToOperator(String email);





    // ********************** BUYER *********************** //
    Account SignUp(Account account);
    Boolean updateBuyer(UserDto userDto);

    Account affectBuyerToSupplier(Long idSupplier,Long idBuyer);







    Boolean disableAccount(UserDto userDto);

    List<Account> getAll();

    Boolean changePassword(String identifiant,String code,String newpassword);


}
