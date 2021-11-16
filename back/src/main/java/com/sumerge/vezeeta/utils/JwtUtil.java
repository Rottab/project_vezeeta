package com.sumerge.vezeeta.utils;

import com.sumerge.vezeeta.security.models.AppUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${vezeeta.app.jwtSecretKey}")
    private String secretKey;

    @Value("${vezeeta.app.jwtExpireIn}")
    private Integer expireIn;

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String getUserIdFromToken(String token) {
        // I should extend the Claims set up the getId method
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("id").toString();
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }


    public String generateJwtToken(AppUserDetails userDetails) {
        // Need to improve this further
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userDetails.getId());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject((userDetails.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expireIn))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // Need to improve this part further
            return false;
        }
    }

}
