package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.entity.AuthRequest;
import com.example.springsecurityjwt.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public HomeController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/")
    public String helloJwt(){
        return "Hello Jwt";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
            );
        }
        catch (Exception exception){
            throw  new Exception("invalid username or password");
        }
        return jwtService.generateToken(authRequest.getUsername());
    }

}
