package com.nischal.security.service;

import com.nischal.security.dto.Response;
import com.nischal.security.request.CreateUserRequest;

public interface UserService {

   // Response currentUser();

    Response save(CreateUserRequest request);

    Response getUser(String email);

    Response getAll();
}
