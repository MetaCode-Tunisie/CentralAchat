package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Dto.UserDto;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Services.IServiceAccount;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/operator") @AllArgsConstructor

public class OperatorController {

    IServiceAccount iServiceAccount;


    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @PostMapping("/update")
    void updateOperator(@RequestBody UserDto userDto){
        iServiceAccount.updateOperator(userDto);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','OPERATOR')")
    @PostMapping("/delete")
    void supprimer(@RequestBody UserDto userDto){
        iServiceAccount.disableAccount(userDto);
    }

}
