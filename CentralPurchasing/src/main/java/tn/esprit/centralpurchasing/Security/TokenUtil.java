package tn.esprit.centralpurchasing.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import tn.esprit.centralpurchasing.Entities.Account;
import tn.esprit.centralpurchasing.Entities.Role;
import java.util.*;
import java.util.stream.Collectors;


@Component @Slf4j
public class TokenUtil {
    @Value("${jwt.secret}")
    private final String TOKEN_SECRET="MetaCode.Tunisie.BestOrganisationForEver";

    public String generateToken(Account account){
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET.getBytes());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role r : account.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(r.getTypeRole().toString()));
        }
        return JWT.create().withSubject(account.getEmail())
                .withExpiresAt(generateExpirationDate())
                .withClaim("roles",
                        authorities.stream().map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .sign(algorithm);
    }

    public String getUserNameFromToken(String token){
             return getClaims(token).getSubject();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 24);
    }


    public boolean isTokenValid(String token, Account account) {
        String username= getUserNameFromToken(token);
        return (username.equals(account.getEmail()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration=getClaims(token).getExpiration();
        return expiration.before(new Date());
    }


    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(token).getBody();
    }

}
