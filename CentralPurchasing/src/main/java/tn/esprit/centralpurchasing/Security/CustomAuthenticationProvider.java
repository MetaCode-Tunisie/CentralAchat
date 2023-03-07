package tn.esprit.centralpurchasing.Security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Entities.Role;
import tn.esprit.centralpurchasing.Repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;

@Component @AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
 private AccountRepository accountRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();


        Account user = accountRepository.findByEmail(email);
        if (user == null) {
            System.out.println("User Not Found In The DataBase");
            throw new UsernameNotFoundException("User Not Found In The DataBase");
        } else if(new BCryptPasswordEncoder().matches(password, user.getPassword()))
        {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (Role r : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(r.getTypeRole().toString()));
            }
            return new UsernamePasswordAuthenticationToken(email, password, authorities);
        }
          else throw new BadCredentialsException("invalid credentials");


    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}
