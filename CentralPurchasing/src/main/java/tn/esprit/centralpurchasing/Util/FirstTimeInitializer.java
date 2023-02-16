package tn.esprit.centralpurchasing.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Services.IServiceAccount;
import tn.esprit.centralpurchasing.Services.IServiceRole;

@Component @AllArgsConstructor
public class FirstTimeInitializer implements CommandLineRunner {

    private IServiceAccount iServiceAccount;
    private IServiceRole iServiceRole;
    @Override
    public void run(String... args) throws Exception {
        /*if(iServiceAccount.getAll().isEmpty())
        {
            Account account =new Account();
            account.setEmail("nasr@nasr.tn");
            account.setPassword("password");
            account.setFirstname("nasreddine");
            iServiceAccount.SignUp(account);


        }*/
        if(iServiceRole.getAllRole().isEmpty())
        {
            iServiceRole.addRoles();
        }

    }
}
