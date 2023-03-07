package tn.esprit.centralpurchasing.Util;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Services.IServiceAccount;
import tn.esprit.centralpurchasing.Services.IServiceRole;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

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

        //Date dateSys = new Date();
        //LocalDateTime.from(dateSys.toInstant()).plusDays(3);


        /*
        Calendar ac = Calendar.getInstance();
        Date datesys=new Date();


        ac.setTime(datesys);
            datesys=ac.getTime();

        Date dateOrder = new Date("23/02/2023");
        Calendar c = Calendar.getInstance();
        c.setTime(dateOrder);
        c.add(Calendar.DATE,2);
        dateOrder = c.getTime();

        System.out.println("\n date order"+dateOrder+"\n"+"date System"+datesys);

        if(datesys.before(dateOrder))
        System.out.println("supprimer date ");

*/




    }
}
