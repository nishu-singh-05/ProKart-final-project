package com.project.authenticationservice.controller;



import com.project.authenticationservice.jwtUtil.JwtUtil;
import com.project.authenticationservice.model.AuthRequest;
import com.project.authenticationservice.model.UserServiceData;
import com.project.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

   /* @Autowired
    private AuthenticationManager authenticationManager;
*/
    @GetMapping("/")
    public String welcome() {
        return "Welcome to Spring Security";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody UserServiceData userServiceData) throws Exception{
        try{

            userRepository.findById(userServiceData.getUserName());
           /* authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword()
                    ));*/
        }
        catch (Exception e) {
            throw  new Exception("Invalid username/password");
        }

        return jwtUtil.generateToken(userServiceData.getUserName());

    }
}


