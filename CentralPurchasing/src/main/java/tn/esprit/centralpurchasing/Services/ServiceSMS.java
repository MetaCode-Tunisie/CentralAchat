package tn.esprit.centralpurchasing.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sun.net.www.URLConnection;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Repository.AccountRepository;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

@Service @AllArgsConstructor
public class ServiceSMS implements IServiceSMS{

    private AccountRepository accountRepository;
    @Override
    public Boolean SendSMS(String to) throws IOException {


        if(to.equals(""))
        {
            return false;
        }
        else
        {
        Account account = accountRepository.findByPhoneNumber(to);
        if(account==null)
            account=accountRepository.findByEmail(to);

        if(account != null)
        {
            account.setCodeTel(String.valueOf(generateCode()));
            System.out.println(account.getCodeTel());
            accountRepository.save(account);
            String message=account.getCodeTel();
            URL url = new URL("http://localhost:9710/http/send-message?to=" + account.getPhoneNumber() + "&message-type=sms.automatic&message=MC:-+"+account.getCodeTel()+"+votre"+"+code"+"+de"+"+validation"+"+MetaCode"+"+Tunisie.");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            System.out.println(con.getResponseMessage());

        }
        else return false;


        }
        return true;
    }

    public int generateCode(){
        Random random = new Random();
        return 100000 + random.nextInt(999999 - 100000);

    }

}
