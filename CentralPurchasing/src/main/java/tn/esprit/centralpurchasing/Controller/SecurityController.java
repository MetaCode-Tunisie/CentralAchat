package tn.esprit.centralpurchasing.Controller;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Security.JwtReponse;
import tn.esprit.centralpurchasing.Security.SignInRequest;
import tn.esprit.centralpurchasing.Security.TokenUtil;
import tn.esprit.centralpurchasing.Services.IServiceAccount;

@RestController
@RequestMapping(value = "/api/v1/auth")
@AllArgsConstructor @Configuration

public class SecurityController {

    private IServiceAccount iServiceAccount;
    private TokenUtil tokenUtil;
    private AuthenticationManager authenticationManager;
    @PostMapping(value = {"","/"})
    public JwtReponse signIn(@RequestBody SignInRequest signInRequest){
        final Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),signInRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Account account = iServiceAccount.loadUserByUsername(signInRequest.getUsername());

        String token= tokenUtil.generateToken(account);
        JwtReponse reponse= new JwtReponse(token);

        return reponse;
    }


}
