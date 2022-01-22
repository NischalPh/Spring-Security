package com.nischal.security.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
public class CreateUserRequest implements Serializable {
    @Min(3)
    @Max(100)
    @Pattern(regexp="[a-zA-Z]")
    @NotEmpty
    private String firstName;
    @Min(3)
    @Max(100)
    @Pattern(regexp="[a-zA-Z]")
    @NotEmpty
    private String middleName;
    @Min(3)
    @Max(100)
    @Pattern(regexp="[a-zA-Z]")
    @NotEmpty
    private String lastName;
    @Pattern(regexp="MALE|FEMALE")
    @NotEmpty
    private String genderType;
    @Min(3)
    @Max(250)
    @NotEmpty
    private String address;
    @Min(3)
    @Max(10)
    @NotEmpty
    private String contactNo;
    @Email
    private String emailAddress;
    private Boolean sendEmail;
    private Boolean isPasswordGenerated;
    @Max(150)
    private String password;
    private Long roleId;
}
