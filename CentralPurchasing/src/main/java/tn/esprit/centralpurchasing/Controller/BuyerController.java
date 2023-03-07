package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Dto.UserDto;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Services.IServiceAccount;

@RestController @AllArgsConstructor
@RequestMapping("/buyer")
public class BuyerController {
    private IServiceAccount iServiceAccount;



    @PostMapping("/update")
    void updateOperator(@RequestBody UserDto userDto){
        iServiceAccount.updateBuyer(userDto);
    }

    @PostMapping("/delete")
    void supprimer(@RequestBody UserDto userDto){
        iServiceAccount.disableAccount(userDto);
    }

    @PutMapping("/affectBuyerToSupplier/{idSupplier}/{idBuyer}")
    Account affectBuyerToSupplier(@PathVariable Long idSupplier,@PathVariable Long idBuyer){
        return iServiceAccount.affectBuyerToSupplier(idSupplier,idBuyer);
    }







}
