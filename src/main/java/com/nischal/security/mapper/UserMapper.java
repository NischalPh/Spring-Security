package com.nischal.security.mapper;


import com.nischal.security.model.User;
import com.nischal.security.response.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserResponse mapUser(User user) {
        UserResponse response = new UserResponse();
        response.setFirstName(user.getFirstName());
        response.setMiddleName(user.getMiddleName());
        response.setLastName(user.getLastName());
        response.setGenderType(user.getGenderType().name());
        response.setEmailAddress(user.getEmailAddress());
        response.setAddress(user.getAddress());
        response.setContactNo(user.getContactNo());
        response.setPassword(user.getPassword());
        response.setRole(user.getRole().getName());
        response.setAuthorities(user.authorities());
        return response;
    }

    public static List<UserResponse> mapUsers(List<User> users) {
        return users
                .stream()
                .map(UserMapper::mapUser)
                .collect(Collectors.toList());
    }
}
