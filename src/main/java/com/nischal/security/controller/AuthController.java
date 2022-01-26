package com.nischal.security.controller;

import com.nischal.security.dto.Response;
import com.nischal.security.facade.LoginFacade;
import com.nischal.security.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    private final LoginFacade loginFacade;

    public AuthController(LoginFacade loginFacade) {
        this.loginFacade = loginFacade;
    }

    @PostMapping("login")
    public ResponseEntity authenticateUser(@RequestBody @Valid LoginRequest request) {
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok();
      Response response= loginFacade.login(request, (httpHeader)->bodyBuilder.headers(httpHeader));
      return bodyBuilder.body(response);
    }
}
