package com.nischal.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity{

    @Column(length = 150, name = "FIRSTNAME", nullable = false)
    private String firstName;

    @Column(length = 150, name = "MIDDLENAME")
    private String middleName;

    @Column(length = 150, name = "LASTNAME", nullable = false)
    private String lastName;

    @Column(name = "GENDER", columnDefinition = "enum('MALE','FEMALE')", nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderType genderType;

    @Column(length = 150, name = "ADDRESS", nullable = false)
    private String address;

    @JsonIgnore
    @Column(length = 20, name = "CONTACT_NO", nullable = false)
    private String contactNo;

    @JsonIgnore
    @Column(length = 150, name = "EMAIL_ADDRESS", nullable = false)
    private String emailAddress;

    @JsonIgnore
    @Column(length = 150, name = "PASSWORD", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    private Role role;

    public List<String> authorities() {
        return role
                .getRoleAuthorities()
                .stream()
                .map(roleAuthority -> roleAuthority.getAuthority().getName())
                .collect(Collectors.toList());
    }
}
