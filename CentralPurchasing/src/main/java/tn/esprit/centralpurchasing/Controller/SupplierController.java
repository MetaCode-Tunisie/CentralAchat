package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Dto.UserDto;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Services.IServiceAccount;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/supplier") @AllArgsConstructor

public class SupplierController {

    IServiceAccount iServiceAccount;


    @PreAuthorize("hasAnyAuthority('ADMIN','SUPPLIER')")
    @PostMapping("/add")
    Account addSupplier(@RequestBody Account account)
    {
        return iServiceAccount.addSupplier(account);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SUPPLIER')")
    @PostMapping("/update")
    void updateOperator(@RequestBody UserDto userDto){
        iServiceAccount.updateSupplier(userDto);
    }

    @PostMapping("/affectSupplierToOperator/{idOperator}/{idSupplier}")
    Account affectSupplierToOperator(@PathVariable Long idOperator, @PathVariable Long idSupplier){
        return iServiceAccount.affectSupplierToOperator(idOperator,idSupplier);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SUPPLIER')")
    @PostMapping("/disaffectSupplierToOperator")
    Boolean disaffectSupplierToOperator(@RequestBody UserDto userDto){
        return iServiceAccount.disaffectSupplierToOperator(userDto.getEmail());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SUPPLIER')")
    @PostMapping("/delete")
    void supprimer(@RequestBody UserDto userDto){
        iServiceAccount.disableAccount(userDto);
    }

}
