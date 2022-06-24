package com.tech1.tech1test.controllers;

import com.tech1.tech1test.domain.User;
import com.tech1.tech1test.dto.AuthenticationRequestDto;
import com.tech1.tech1test.security.jwt.JwtProvider;
import com.tech1.tech1test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtProvider jwtProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }
    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            User user = userService.findByName(username);
            String token = jwtProvider.createToken(username, user.getRoles());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            if (user == null) {
                throw new UsernameNotFoundException("User with username " + username + " not found");
            }

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
