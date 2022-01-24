package com.nischal.security.controller;

import com.nischal.security.dto.Response;
import com.nischal.security.facade.LoginManager;
import com.nischal.security.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    private final LoginManager loginManager;

    public AuthController(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @PostMapping("login")
    public ResponseEntity authenticateUser(@RequestBody @Valid LoginRequest request) {
      return loginManager.login(request);
    }
}
