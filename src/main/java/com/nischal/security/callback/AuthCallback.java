package com.nischal.security.callback;

import org.springframework.http.HttpHeaders;

@FunctionalInterface
public interface AuthCallback {

     void patch(HttpHeaders headers);
}
