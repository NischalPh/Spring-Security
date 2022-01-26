package com.nischal.security.util;

import com.nischal.security.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtUtil {

    public static String generateToken(UserDetails userDetails) {

        User user = (User) userDetails;

        Date issuedDate = new Date();
        Date expiryDate = new Date(issuedDate.getTime() + 600000);
        String secretKey = "mbA7W_&3Gg'a'{.Z!@4724:%=rF.YjuA";
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
}
