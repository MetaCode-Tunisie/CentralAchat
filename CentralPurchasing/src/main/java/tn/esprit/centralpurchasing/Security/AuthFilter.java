package tn.esprit.centralpurchasing.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

public class AuthFilter extends OncePerRequestFilter {
    @Value("${auth.header}")
    private String TOKEN_HEADER = "Authorization";
    @Value("${jwt.secret}")
    private final String TOKEN_SECRET="MetaCode.Tunisie.BestOrganisationForEver";
    private final String TOKEN_ROLES="roles";

    public AuthFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(TOKEN_HEADER);
        final SecurityContext securityContext = SecurityContextHolder.getContext();

        if(header != null && securityContext.getAuthentication()==null){
           String token = header.substring("Bearer ".length());

            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET.getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String username = decodedJWT.getSubject();
            String[] roles = decodedJWT.getClaim(TOKEN_ROLES).asArray(String.class);
            Stream<String> stream1 = Arrays.stream(roles);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            stream1.forEach(role -> { authorities.add(new SimpleGrantedAuthority(role)); });
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request,response);
    }
}
