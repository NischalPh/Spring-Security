package com.nischal.security.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class RoleResponse implements Serializable {

    private String name;
    private List<String> authorities;
}
