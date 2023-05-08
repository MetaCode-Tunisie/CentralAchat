package tn.esprit.centralpurchasing.Services;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Repository.AccountRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service @AllArgsConstructor
public class ServiceEmail implements IServiceEmail{
    JavaMailSender emailSender;

    AccountRepository accountRepository;


    @Override
    public Boolean SendEmail(String identifiant) throws MessagingException {
        Account account= accountRepository.findByEmail(identifiant);

        if(account==null)
            account=accountRepository.findByPhoneNumber(identifiant);

        if(account==null)
            return false;

        String token = UUID.randomUUID().toString();
        account.setResetToken(token);

        setMessage(account.getEmail(),"Reset Password !",token);
        accountRepository.save(account);

        return true;
    }


    @Override
    public void setMessage(String to,String subject,String token) throws MessagingException {

        MimeMessage message= emailSender.createMimeMessage();
        MimeMessageHelper mailHelper = new MimeMessageHelper(message,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        mailHelper.setFrom("metacode.tunisie@gmail.com");
        mailHelper.setTo(to);
        mailHelper.setSubject("Reset Password !");
        mailHelper.setText("","<!DOCTYPE html>\n" +
                "\n" +
                "<html lang='en' xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:v='urn:schemas-microsoft-com:vml'>\n" +
                "<head>\n" +
                "<title></title>\n" +
                "<meta content='text/html; charset=utf-8' http-equiv='Content-Type'/>\n" +
                "<meta content='width=device-width, initial-scale=1.0' name='viewport'/>\n" +
                "<link href='https://fonts.googleapis.com/css?family=Roboto+Slab' rel='stylesheet' type='text/css'/>\n" +
                "<style>\n" +
                "\t\t* {\n" +
                "\t\t\tbox-sizing: border-box;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tbody {\n" +
                "\t\t\tmargin: 0;\n" +
                "\t\t\tpadding: 0;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\ta[x-apple-data-detectors] {\n" +
                "\t\t\tcolor: inherit !important;\n" +
                "\t\t\ttext-decoration: inherit !important;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t#MessageViewBody a {\n" +
                "\t\t\tcolor: inherit;\n" +
                "\t\t\ttext-decoration: none;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tp {\n" +
                "\t\t\tline-height: inherit\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t.desktop_hide,\n" +
                "\t\t.desktop_hide table {\n" +
                "\t\t\tmso-hide: all;\n" +
                "\t\t\tdisplay: none;\n" +
                "\t\t\tmax-height: 0px;\n" +
                "\t\t\toverflow: hidden;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\t@media (max-width:670px) {\n" +
                "\n" +
                "\t\t\t.desktop_hide table.icons-inner,\n" +
                "\t\t\t.social_block.desktop_hide .social-table {\n" +
                "\t\t\t\tdisplay: inline-block !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.icons-inner {\n" +
                "\t\t\t\ttext-align: center;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.icons-inner td {\n" +
                "\t\t\t\tmargin: 0 auto;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.image_block img.big,\n" +
                "\t\t\t.row-content {\n" +
                "\t\t\t\twidth: 100% !important;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.mobile_hide {\n" +
                "\t\t\t\tdisplay: none;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.stack .column {\n" +
                "\t\t\t\twidth: 100%;\n" +
                "\t\t\t\tdisplay: block;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.mobile_hide {\n" +
                "\t\t\t\tmin-height: 0;\n" +
                "\t\t\t\tmax-height: 0;\n" +
                "\t\t\t\tmax-width: 0;\n" +
                "\t\t\t\toverflow: hidden;\n" +
                "\t\t\t\tfont-size: 0px;\n" +
                "\t\t\t}\n" +
                "\n" +
                "\t\t\t.desktop_hide,\n" +
                "\t\t\t.desktop_hide table {\n" +
                "\t\t\t\tdisplay: table !important;\n" +
                "\t\t\t\tmax-height: none !important;\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t</style></head><body style='background-color: #85a4cd; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;'><table border='0' cellpadding='0' cellspacing='0' class='nl-container' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #85a4cd;' width='100%'>\n" +
                "<tbody><tr><td>\n" +
                "<table align='center' border='0' cellpadding='0' cellspacing='0' class='row row-1' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f3f6fe;' width='100%'><tbody><tr><td><table align='center' border='0' cellpadding='0' cellspacing='0' class='row-content stack' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 650px;' width='650'>\n" +
                "<tbody><tr><td class='column column-1' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 15px; padding-bottom: 15px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;' width='100%'>\n" +
                "<table border='0' cellpadding='0' cellspacing='0' class='image_block block-1' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt;' width='100%'>\n" +
                "<tr><td class='pad' style='width:100%;padding-right:0px;padding-left:0px;'><div align='center' class='alignment' style='line-height:10px'><img alt='Your Logo' src='https://www.arabsharing.com/do.php?img=309761' style='display: block; height: auto; border: 0; width: 130px; max-width: 100%;' title='MetaCode Tunisie' width='130'/></div>\n" +
                "</td></tr></table></td></tr></tbody></table></td></tr></tbody></table>\n" +
                "<table align='center' border='0' cellpadding='0' cellspacing='0' class='row row-2' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt;' width='100%'>\n" +
                "<tbody><tr><td><table align='center' border='0' cellpadding='0' cellspacing='0' class='row-content stack' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 650px;' width='650'>\n" +
                "<tbody><tr><td class='column column-1' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 5px; padding-bottom: 5px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;' width='100%'>\n" +
                "<table border='0' cellpadding='0' cellspacing='0' class='heading_block block-2' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt;' width='100%'>\n" +
                "<tr>\n" +
                "<td class='pad' style='padding-bottom:10px;text-align:center;width:100%;padding-top:60px;'>\n" +
                "<h1 style='margin: 0; color: #ffffff; direction: ltr; font-family: 'Roboto Slab', Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 30px; font-weight: normal; letter-spacing: 2px; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0;'><strong>FORGOT YOUR PASSWORD?</strong></h1>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table border='0' cellpadding='0' cellspacing='0' class='image_block block-3' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt;' width='100%'>\n" +
                "<tr>\n" +
                "<td class='pad' style='width:100%;padding-right:0px;padding-left:0px;'>\n" +
                "<div align='center' class='alignment' style='line-height:10px'><img alt='Wrong Password Animation' class='big' src='https://www.arabsharing.com/do.php?img=309871' style='display: block; height: auto; border: 0; width: 500px; max-width: 100%;'  width='500'/></div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table border='0' cellpadding='0' cellspacing='0' class='text_block block-5' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;' width='100%'>\n" +
                "<tr>\n" +
                "<td class='pad' style='padding-bottom:5px;padding-left:10px;padding-right:10px;padding-top:25px;'>\n" +
                "<div style='font-family: sans-serif'>\n" +
                "<div class='' style='font-size: 14px; font-family: Roboto Slab, Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 16.8px; color: #3f4d75; line-height: 1.2;'>\n" +
                "<p style='margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 16.8px;'><span style='font-size:20px;'>Don't worry, be happy!</span></p>\n" +
                "</div>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table border='0' cellpadding='0' cellspacing='0' class='text_block block-6' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;' width='100%'>\n" +
                "<tr>\n" +
                "<td class='pad' style='padding-bottom:5px;padding-left:10px;padding-right:10px;padding-top:5px;'>\n" +
                "<div style='font-family: sans-serif'>\n" +
                "<div class='' style='font-size: 14px; font-family: Roboto Slab, Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 16.8px; color: #3f4d75; line-height: 1.2;'>\n" +
                "<p style='margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 16.8px;'><span style='font-size:22px;'>Let's get yoy a new password.</span></p>\n" +
                "</div>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table border='0' cellpadding='0' cellspacing='0' class='button_block block-8' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt;' width='100%'>\n" +
                "<tr>\n" +
                "<td class='pad' style='padding-bottom:10px;padding-left:10px;padding-right:10px;padding-top:30px;text-align:center;'>\n" +
                "<div align='center' class='alignment'>\n" +
                "<a href='"+token+"' style='text-decoration:none;display:inline-block;color:#ffffff;background-color:#f16722;border-radius:10px;width:auto;border-top:3px solid #3F4D75;font-weight:undefined;border-right:3px solid #3F4D75;border-bottom:3px solid #3F4D75;border-left:3px solid #3F4D75;padding-top:10px;padding-bottom:10px;font-family:Roboto Slab, Arial, Helvetica Neue, Helvetica, sans-serif;font-size:18px;text-align:center;mso-border-alt:none;word-break:keep-all;' target='_blank'><span style='padding-left:25px;padding-right:25px;font-size:18px;display:inline-block;letter-spacing:normal;'><span dir='ltr' style='word-break: break-word;'><span data-mce-style='' dir='ltr' style='line-height: 36px;'>RESET MY PASSWORD</span></span></span></a>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<table border='0' cellpadding='0' cellspacing='0' class='text_block block-10' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;' width='100%'>\n" +
                "<tr>\n" +
                "<td class='pad' style='padding-bottom:40px;padding-left:10px;padding-right:10px;padding-top:30px;'>\n" +
                "<div style='font-family: sans-serif'>\n" +
                "<div class='' style='font-size: 14px; font-family: Roboto Slab, Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 16.8px; color: #3f4d75; line-height: 1.2;'>\n" +
                "<p style='margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 16.8px;'><span style='font-size:14px;'>If you didn’t request to change your password, simply ignore this email.</span></p>\n" +
                "</div></div></td></tr></table></td></tr></tbody></table></td></tr></tbody></table><table align='center' border='0' cellpadding='0' cellspacing='0' class='row row-3' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #c4d6ec;' width='100%'>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td>\n" +
                "<table align='center' border='0' cellpadding='0' cellspacing='0' class='row-content stack' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 650px;' width='650'>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td class='column column-1' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 20px; padding-bottom: 20px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;' width='100%'>\n" +
                "<table border='0' cellpadding='10' cellspacing='0' class='text_block block-1' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;' width='100%'>\n" +
                "<tr>\n" +
                "<td class='pad'>\n" +
                "<div style='font-family: sans-serif'>\n" +
                "<div class='' style='font-size: 14px; font-family: Roboto Slab, Arial, Helvetica Neue, Helvetica, sans-serif; mso-line-height-alt: 16.8px; color: #3f4d75; line-height: 1.2;'>\n" +
                "<p style='margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 16.8px;'><span style='font-size:12px;'>This link will expire in 24 hours. If you continue to have problems</span><br/><span style='font-size:12px;'>please feel free to contact us at <a href='mailto:metacode.tunisie@gmail.com' rel='noopener' style='text-decoration: underline; color: #ffffff;' target='_blank' title='metacode.tunisie@gmail.com'>metacode.tunisie@gmail.com</a>. <a href='https://www.example.com' rel='noopener' style='text-decoration: underline; color: #ffffff;' target='_blank'>UNSUBSCRIBE</a></span></p></div></div></td></tr></table></td></tr></tbody></table></td></tr></tbody></table><table align='center' border='0' cellpadding='0' cellspacing='0' class='row row-4' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #f3f6fe;' width='100%'>\n" +
                "<tbody><tr><td>\n" +
                "<table align='center' border='0' cellpadding='0' cellspacing='0' class='row-content stack' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 650px;' width='650'>\n" +
                "<tbody><tr>\n" +
                "<td class='column column-1' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; padding-top: 0px; padding-bottom: 0px; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;' width='100%'>\n" +
                "<table border='0' cellpadding='0' cellspacing='0' class='social_block block-1' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt;' width='100%'><tr>\n" +
                "<td class='pad' style='padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;text-align:center;'>\n" +
                "<div align='center' class='alignment'>\n" +
                "<table border='0' cellpadding='0' cellspacing='0' class='social-table' role='presentation' style='mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block;' width='208px'>\n" +
                "<tr>\n" +
                "<td style='padding:0 10px 0 10px;'><a href='https://www.facebook.com/' target='_blank'><img alt='Facebook' height='32' src='https://www.arabsharing.com/do.php?img=309873' style='display: block; height: auto; border: 0;' title='facebook' width='32'/></a></td>\n" +
                "<td style='padding:0 10px 0 10px;'><a href='https://www.twitter.com/' target='_blank'><img alt='Twitter' height='32' src='https://www.arabsharing.com/do.php?img=309872' style='display: block; height: auto; border: 0;' title='twitter' width='32'/></a></td>\n" +
                "<td style='padding:0 10px 0 10px;'><a href='https://www.linkedin.com/' target='_blank'><img alt='Linkedin' height='32' src='https://www.arabsharing.com/do.php?img=309875' style='display: block; height: auto; border: 0;' title='linkedin' width='32'/></a></td>\n" +
                "<td style='padding:0 10px 0 10px;'><a href='https://www.instagram.com/' target='_blank'><img alt='Instagram' height='32' src='https://www.arabsharing.com/do.php?img=309874' style='display: block; height: auto; border: 0;' title='instagram' width='32'/></a></td></tr></table></div></td></tr></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></body></html>");

        this.emailSender.send(message);

    }
}
