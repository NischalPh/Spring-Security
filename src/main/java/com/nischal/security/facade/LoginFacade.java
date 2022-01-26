package com.nischal.security.facade;

import com.nischal.security.builder.ResponseBuilder;
import com.nischal.security.callback.AuthCallback;
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
public class LoginFacade {

    private final UserDetailsService userDetailsService;

    public LoginFacade(UserDetailsService userService) {
        this.userDetailsService = userService;
    }

    public Response login(LoginRequest request, AuthCallback callback) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmailAddress());
        if (request.getPassword().equals(userDetails.getPassword())) {
            String token = JwtUtil.generateToken(userDetails);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, token);
            callback.patch(headers);
            return ResponseBuilder.success("login successful");
        }
        return ResponseBuilder.success("login failed");
    }
}
