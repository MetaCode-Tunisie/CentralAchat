package tn.esprit.centralpurchasing;


import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tn.esprit.centralpurchasing.Config.TwilioConfig;

import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
public class CentralPurchasingApplication {


    @Autowired
    private TwilioConfig twilioConfig;
    @PostConstruct
    public void initTwilio(){
        //Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
    }


    public static void main(String[] args) {
        SpringApplication.run(CentralPurchasingApplication.class, args);
    }

}
