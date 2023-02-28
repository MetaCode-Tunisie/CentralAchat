package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Services.IServiceAccount;

import java.util.List;

@RestController
@RequestMapping("/admin") @AllArgsConstructor

public class AdminController {

    IServiceAccount iServiceAccount;



    //**************************************** ADMIN *******************************************//

    @PostMapping("/signup")
    Account SignUpAdmin(@RequestBody Account account)
    {
        return iServiceAccount.SignUpAdmin(account);
    }

    @GetMapping("/getall")
    List<Account> getAccounts()
    {
        return iServiceAccount.getAll();
    }



    @PostMapping("/addoperator")
    Account addOperator(@RequestBody Account account)
    {
        return iServiceAccount.addOperator(account);
    }



}
