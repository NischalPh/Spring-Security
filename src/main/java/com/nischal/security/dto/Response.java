package com.nischal.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
public class Response implements Serializable {

    private Boolean success;
    private String message;
    private Object data;
}
