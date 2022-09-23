package com.project.authenticationservice.controller;



import com.project.authenticationservice.jwtUtil.JwtUtil;
import com.project.authenticationservice.model.UserServiceData;
import com.project.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SecurityController {


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/authenticate")
    public ResponseEntity generateToken(@RequestBody UserServiceData userServiceData) throws Exception{
        UserServiceData user=userRepository.findByUserNameAndPasswordAndRole(userServiceData.getUserName(), userServiceData.getPassword(), userServiceData.getRole());

        ResponseEntity response;

        if (user!=null){

            response = new ResponseEntity(jwtUtil.generateToken(userServiceData.getUserName(),userServiceData.getPassword(), userServiceData.getRole()), HttpStatus.OK);


        }
       else  {
            response = new ResponseEntity("Invalid Username /Password",HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return  response;

    }
}


