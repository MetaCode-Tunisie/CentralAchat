package tn.esprit.centralpurchasing.Services;

import com.lowagie.text.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail,
                                String body,
                                String subject) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("metacode2023@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail Send...");
    }

    public void sendEmailWithAttachment(String toEmail,
                                        String body,
                                        String subject) throws MessagingException, IOException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        Multipart emailcontent = new MimeMultipart();
        MimeMessageHelper mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("metacode2023@gmail.com");
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);
        MimeBodyPart PDFBodyPart = new MimeBodyPart();
        PDFBodyPart.attachFile("C:\\Users\\MSI\\IdeaProjects\\CentralPurchasing\\src\\Factures\\Facture.pdf");
        emailcontent.addBodyPart(PDFBodyPart);
        mimeMessage.setContent(emailcontent);

        mailSender.send(mimeMessage);
        System.out.println("Mail Send...");

    }
}
