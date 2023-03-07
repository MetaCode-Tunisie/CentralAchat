package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Dto.UserDto;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Services.IServiceAccount;

@RestController
@RequestMapping("/supplier") @AllArgsConstructor

public class SupplierController {

    IServiceAccount iServiceAccount;


    @PostMapping("/add")
    Account addSupplier(@RequestBody Account account)
    {
        return iServiceAccount.addSupplier(account);
    }


    @PostMapping("/update")
    void updateOperator(@RequestBody UserDto userDto){
        iServiceAccount.updateSupplier(userDto);
    }

    @PostMapping("/affectSupplierToOperator/{idOperator}/{idSupplier}")
    Account affectSupplierToOperator(@PathVariable Long idOperator, @PathVariable Long idSupplier){
        return iServiceAccount.affectSupplierToOperator(idOperator,idSupplier);
    }

    @PostMapping("/disaffectSupplierToOperator")
    Boolean disaffectSupplierToOperator(@RequestBody UserDto userDto){
        return iServiceAccount.disaffectSupplierToOperator(userDto.getEmail());
    }

    @PostMapping("/delete")
    void supprimer(@RequestBody UserDto userDto){
        iServiceAccount.disableAccount(userDto);
    }

}
