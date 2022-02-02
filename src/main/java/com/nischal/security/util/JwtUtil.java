package com.nischal.security.util;

import com.nischal.security.exception.AuthenticationFailedException;
import com.nischal.security.exception.JwtTokenExpiredException;
import com.nischal.security.model.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.expiresInMinutes}")
    private Long expiresInMinutes;

    public String generateToken(UserDetails userDetails) {

        User user = (User) userDetails;

        Date issuedDate = new Date();
        Date expiryDate = new Date(issuedDate.getTime() + 60000 * expiresInMinutes);
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        claims.put("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")));
        claims.put("email", user.getEmailAddress());

        return Jwts
                .builder()
                .setSubject(new StringBuilder(
                        user.getFirstName())
                        .append(" ")
                        .append(user.getLastName())
                        .toString())
                .setClaims(claims)
                .setIssuedAt(issuedDate)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

    }

    public boolean validateToken(String jwtToken) {
        try {
            Jwt parse = Jwts.parser().setSigningKey(secretKey).parse(jwtToken);
            return true;
        } catch (ExpiredJwtException exception) {
            throw new JwtTokenExpiredException("jwt token is expired");
        } catch (Exception exception) {
            throw new AuthenticationFailedException();
        }
    }

    public Jws<Claims> getData(String jwtToken) {
        return Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtToken);
    }
}
