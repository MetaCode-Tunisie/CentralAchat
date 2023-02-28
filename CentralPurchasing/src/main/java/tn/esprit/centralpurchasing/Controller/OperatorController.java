package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Dto.UserDto;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Services.IServiceAccount;

@RestController
@RequestMapping("/operator") @AllArgsConstructor

public class OperatorController {

    IServiceAccount iServiceAccount;



    @PostMapping("/update")
    void updateOperator(@RequestBody UserDto userDto){
        iServiceAccount.updateOperator(userDto);
    }

    @PostMapping("/delete")
    void supprimer(@RequestBody UserDto userDto){
        iServiceAccount.disableAccount(userDto);
    }

}
