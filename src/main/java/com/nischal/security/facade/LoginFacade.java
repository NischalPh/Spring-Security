package com.nischal.security.facade;

import com.nischal.security.builder.ResponseBuilder;
import com.nischal.security.callback.AuthCallback;
import com.nischal.security.constant.SecurityConstants;
import com.nischal.security.dto.Response;
import com.nischal.security.request.LoginRequest;
import com.nischal.security.util.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class LoginFacade {

    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public LoginFacade(UserDetailsService userService, JwtUtil jwtUtil) {
        this.userDetailsService = userService;
        this.jwtUtil = jwtUtil;
    }

    public Response login(LoginRequest request, AuthCallback callback) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmailAddress());
        if (request.getPassword().equals(userDetails.getPassword())) {
            String token = jwtUtil.generateToken(userDetails);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, new StringBuilder(token)
                    .insert(0, SecurityConstants.JWT_PREFIX)
                    .toString());
            callback.patch(headers);
            return ResponseBuilder.success("login successful");
        }
        return ResponseBuilder.success("login failed");
    }
}
