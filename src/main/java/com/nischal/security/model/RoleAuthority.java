package com.nischal.security.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleAuthority extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    @JsonBackReference
    private Role role;

    @ManyToOne
    @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")
    private Authority authority;
}
