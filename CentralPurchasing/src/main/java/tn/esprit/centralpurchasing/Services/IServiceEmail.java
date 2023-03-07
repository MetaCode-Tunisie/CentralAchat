package tn.esprit.centralpurchasing.Services;

import javax.mail.MessagingException;

public interface IServiceEmail {
    void setMessage(String to,String subject,String token) throws MessagingException;

    Boolean SendEmail(String identifiant) throws MessagingException;
}
