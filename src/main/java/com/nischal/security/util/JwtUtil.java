package com.nischal.security.util;

import com.nischal.security.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class JwtUtil {

    public static String generateToken(UserDetails userDetails){

        User user = (User)userDetails;

        Date issuedDate = new Date();

        return Jwts
                .builder()
                .setSubject(new StringBuilder(
                        user
                        .getFirstName())
                        .append(" ")
                        .append(user.getLastName())
                        .append(" ")
                        .append(user.getEmailAddress())
                        .toString())
                .setIssuedAt(issuedDate)
                .setExpiration(new Date(issuedDate.getTime()+600000))
                .signWith(SignatureAlgorithm.HS256, "mbA7W_&3Gg'a'{.Z!@4724:%=rF.YjuA")
                .compact();

    }
}
