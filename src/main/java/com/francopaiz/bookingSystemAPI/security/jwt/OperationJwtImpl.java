package com.francopaiz.bookingSystemAPI.security.jwt;

import com.francopaiz.bookingSystemAPI.dto.UserRepositoryDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class OperationJwtImpl implements OperationJwt{

    @Value("${security.jwt.secret.key}")
    private String secretKey;

    @Override
    public String generateToken(UserRepositoryDto user, Calendar calendar) {
        return Jwts.builder()
                .setSubject(String.valueOf(user.getIdUser()))
                .claim("User", user.getUsername())
                .claim("ada_roles", user.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact()
                ;

    }

    /*private SecretKey generateKey(){
        byte[] passwordDecoded = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(passwordDecoded);
    }*/
}
