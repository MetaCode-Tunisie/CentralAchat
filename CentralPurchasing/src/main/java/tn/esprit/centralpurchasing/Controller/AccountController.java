package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Services.IServiceAccount;

@RestController @RequestMapping("") @AllArgsConstructor
public class AccountController {

    private final IServiceAccount iServiceAccount;

    //**************************************** ADMIN *******************************************//
    @PostMapping("admin/signUp")
    Account SignUpAdmin(@RequestBody Account account)
    {
        return iServiceAccount.SignUpAdmin(account);
    }

    //**************************************** OPERATOR *******************************************//


    @PostMapping("operator/add")
    Account addOperator(@RequestBody Account account)
    {
        return iServiceAccount.addOperator(account);
    }



    //**************************************** SUPPLIER *******************************************//

    @PostMapping("supplier/add")
    Account addSupplier(@RequestBody Account account)
    {
        return iServiceAccount.addSupplier(account);
    }
    @PutMapping("supplier/affectSupplierToOperator/{idOperator}/{idSupplier}")
    Account affectSupplierToOperator(@PathVariable Long idOperator,@PathVariable Long idSupplier){
        return iServiceAccount.affectSupplierToOperator(idOperator,idSupplier);
    }


    //**************************************** BUYER *******************************************//

    @PostMapping("buyer/signUp")
    Account SignUp(@RequestBody Account account)
    {
        return iServiceAccount.SignUp(account);
    }

    @PutMapping("buyer/affectBuyerToSupplier/{idSupplier}/{idBuyer}")
    Account affectBuyerToSupplier(@PathVariable Long idSupplier,@PathVariable Long idBuyer){
        return iServiceAccount.affectBuyerToSupplier(idSupplier,idBuyer);
    }

    @PutMapping("buyer/disable/{idBuyer}")
    Account disableBuyer(@PathVariable Long idBuyer)
    {
        return iServiceAccount.disableBuyer(idBuyer);
    }




}
