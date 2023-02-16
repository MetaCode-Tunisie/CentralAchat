package tn.esprit.centralpurchasing.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import tn.esprit.centralpurchasing.Entities.Account;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {
    private final String CLAIMS_SUBJECT="sub";
    private final String CLAIMS_CREATED="created";
    private String TOKEN_SECRET="todoAPISecret1234";
    private Long TOKEN_VALIDITY=604800L;

    public String generateToken(Account account){
        Map<String,Object> claims=new HashMap<>();
        claims.put(CLAIMS_SUBJECT,account.getEmail());
        claims.put(CLAIMS_CREATED,new Date());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,TOKEN_SECRET)
                .compact();
    }

    public Date generateExpirationDate() {
    return new Date(System.currentTimeMillis()+TOKEN_VALIDITY*1000);
    }
}
