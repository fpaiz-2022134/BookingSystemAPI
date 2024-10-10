package com.francopaiz.bookingSystemAPI.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import com.francopaiz.bookingSystemAPI.controller.auth.TokenDto;
import com.francopaiz.bookingSystemAPI.model.user.RoleEnum;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.francopaiz.bookingSystemAPI.utils.Constants.CLAIMS_ROLES_KEY;


@Component
public class JwtUtil {

    private final JwtConfig jwtConfig;

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public TokenDto generateToken(String username, List<RoleEnum> roles) {

        Date expirationDate = jwtConfig.getExpirationDate();
        String token = Jwts.builder().subject(username)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .claim(CLAIMS_ROLES_KEY, roles)
                .signWith(jwtConfig.getSigningKey())
                .compact();

        System.out.println("TOKEN: " + token);
        System.out.println("Expiration Date: " + expirationDate);
        System.out.println("Current time: " + new Date());
        return new TokenDto(token, expirationDate);
    }

    public Claims extractAndVerifyClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    // Método para validar el token
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            Claims claims = extractAndVerifyClaims(token);
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();

            // Verifica si el token pertenece al usuario correcto y no ha expirado
            boolean isExpired = isTokenExpired(expirationDate);
            System.out.println("Token is expired: " + isExpired);

            return username.equals(userDetails.getUsername()) && !isExpired;
        } catch (ExpiredJwtException e) {
            System.out.println("El token ha expirado.");
            return false;
        } catch (SignatureException e) {
            System.out.println("Firma del token inválida.");
            return false;
        } catch (Exception e) {
            System.out.println("Error al validar el token: " + e.getMessage());
            return false;
        }
    }

    // Verifica si el token ha expirado
    private boolean isTokenExpired(Date expirationDate) {
        return expirationDate.before(new Date());
    }


}