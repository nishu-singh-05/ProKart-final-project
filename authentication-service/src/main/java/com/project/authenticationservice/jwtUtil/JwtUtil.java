package com.project.authenticationservice.jwtUtil;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import java.util.Date;


@Service
public class JwtUtil {
    private static final String secret = "SECRET_KEY";

    //generate token
    public String generateToken(String username) {
       return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, secret).compact();

    }

}

