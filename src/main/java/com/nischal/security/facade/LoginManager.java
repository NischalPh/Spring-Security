package com.nischal.security.facade;

import com.nischal.security.builder.ResponseBuilder;
import com.nischal.security.dto.Response;
import com.nischal.security.mapper.UserMapper;
import com.nischal.security.model.User;
import com.nischal.security.request.LoginRequest;
import com.nischal.security.response.UserResponse;
import com.nischal.security.util.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class LoginManager {

    private final UserDetailsService userDetailsService;

    public LoginManager(UserDetailsService userService) {
        this.userDetailsService = userService;
    }

    public ResponseEntity login(LoginRequest request) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmailAddress());
        if (request.getPassword().equals(userDetails.getPassword())) {
            String token = JwtUtil.generateToken(userDetails);
            UserResponse userResponse = UserMapper.mapUser((User)userDetails);
            HttpHeaders headers = new HttpHeaders();
            headers.add("authorization", token);
            return ResponseEntity.ok().headers(headers).body(userResponse);
        }
        return ResponseEntity.badRequest().body("login failed");
    }
}
