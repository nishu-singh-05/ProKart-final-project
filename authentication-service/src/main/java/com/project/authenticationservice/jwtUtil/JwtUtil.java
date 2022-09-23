package com.project.authenticationservice.jwtUtil;


import com.project.authenticationservice.model.UserServiceData;
import com.project.authenticationservice.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;


@Service
public class JwtUtil {
    private static final String secret = "SECRET_KEY";

    @Autowired
    private UserRepository userRepository;

    //generate token
    public String generateToken(String username, String password,String role) {


            return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                    .signWith(SignatureAlgorithm.HS256, secret).compact();


    }
}

