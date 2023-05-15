package com.springboot.blog.controller;

import com.springboot.blog.payload.JWTAuthResponse;
import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.RegisterDto;
import com.springboot.blog.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    //Login Rest API
    /* Example request:
    http POST http://localhost:8080/api/auth/login \
    Content-Type:application/json \
    usernameOrEmail="John Doe" \
    password= "password"
     */
    @PostMapping(value = {"/login", "/sign-in"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    //Register Rest API
    /* Example request:
    http POST http://localhost:8080/api/auth/register \
    Content-Type:application/json \
    name="John Doe" \
    username="John_Doe" \
    email= mail@mail.com
    password= "password"
     */
    @PostMapping(value = {"/register", "/sign-up"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerDto));
    }


}
