package com.nischal.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity{

    @Column(name = "NAME", length = 150, nullable = false)
    private String name;

    @OneToMany(mappedBy = "role",fetch = FetchType.EAGER)
    private List<RoleAuthority> roleAuthorities;

    public Role(Long id) {
        super(id);
    }
}
