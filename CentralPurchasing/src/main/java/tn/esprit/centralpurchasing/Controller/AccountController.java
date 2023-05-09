package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.centralpurchasing.Dto.ResetpasswordDto;
import tn.esprit.centralpurchasing.Dto.TokenDto;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Entities.Gender;
import tn.esprit.centralpurchasing.Entities.TypeRole;
import tn.esprit.centralpurchasing.Dto.UserDto;
import tn.esprit.centralpurchasing.Security.TokenUtil;
import tn.esprit.centralpurchasing.Services.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Base64;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
@CrossOrigin(origins = "http://localhost:4200")

@RestController @RequestMapping("/account") @AllArgsConstructor
public class AccountController {

    private final IServiceAccount iServiceAccount;
    private final IServiceSMS iServiceSMS;
    private final IServiceEmail iServiceEmail;
    private final AuthenticationManager authenticationManager;
    private TokenUtil tokenUtil;

// ********************************* SIGN UP *******************************
//@PreAuthorize("hasAnyAuthority('ADMIN')")



    @PostMapping(path= "/signup/{firstname}/{lastname}/{phone}/{email}/{password}/{gender}",consumes = {MULTIPART_FORM_DATA_VALUE })
    Account SignUp(@RequestParam MultipartFile file,@PathVariable String firstname,@PathVariable String lastname ,@PathVariable String phone,@PathVariable String email,@PathVariable String password,@PathVariable Gender gender) throws IOException {
        Account account=new Account();
        account.setPassword(password);account.setPhoneNumber(phone);account.setFirstname(firstname);account.setLastname(lastname);
        account.setEmail(email);account.setGender(gender);
        account.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
        return iServiceAccount.SignUp(account);
    }



// **********************************  LOGIN **********************************

    @PostMapping("/login")
    public TokenDto login(@RequestBody UserDto userDto){
        Authentication authentication;
        authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Account account=iServiceAccount.findByUsername(userDto.getEmail());
        String token=tokenUtil.generateToken(account);
        TokenDto tokenDto=new TokenDto();
        tokenDto.setToken(token);
        return tokenDto;
    }

    @PostMapping("/firstlogin")
    public String sendmail() throws MessagingException {
        iServiceEmail.setMessage("nasreddine.elmadhkour@gmail.com","testSubject","hello");
        return "Message sent";
    }








    //*********************** RESET PASSWORD ACCOUNT ********************************

    @PostMapping("/resetpasswordmobile/{identifiant}")
    public Boolean codeVerification(@PathVariable String identifiant) throws IOException {
        return iServiceSMS.SendSMS(identifiant);
    }
    @PostMapping("/resetpasswordemail/{identifiant}")
    public Boolean linkVerification(@PathVariable String identifiant) throws MessagingException {
        return iServiceEmail.SendEmail(identifiant);
    }

    @PostMapping("/newpassword")
    public Boolean changepassword(@RequestBody ResetpasswordDto resetpassword)
    {
        return iServiceAccount.changePassword(resetpassword.getIdentifiant(),resetpassword.getCode(),resetpassword.getNewPassword());
    }












    @PreAuthorize("hasAnyAuthority('BUYER')")
    @GetMapping("/test")
    String chaine(){
        return TypeRole.OPERATOR.name();
    }






    @PreAuthorize("hasAnyAuthority('ADMIN','SUPPLIER')")
    @GetMapping("/supplier")
    String getsupplier(){
        return TypeRole.SUPPLIER.name();
    }





}
