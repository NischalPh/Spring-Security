package com.nischal.security.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class LoginRequest implements Serializable {

    @NotBlank
    @Email
    private String emailAddress;
    @NotBlank
    private String password;
}
