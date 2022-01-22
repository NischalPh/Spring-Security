package com.nischal.security.service.impl;


import com.nischal.security.builder.ResponseBuilder;
import com.nischal.security.builder.UserBuilder;
import com.nischal.security.dto.Response;
import com.nischal.security.exception.DataNotFoundException;
import com.nischal.security.exception.SystemException;
import com.nischal.security.mapper.UserMapper;
import com.nischal.security.model.User;
import com.nischal.security.repository.UserRepository;
import com.nischal.security.request.CreateUserRequest;
import com.nischal.security.response.UserResponse;
import com.nischal.security.service.UserService;
import com.nischal.security.util.LogUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserBuilder userBuilder;

    public UserServiceImpl(UserRepository userRepository, UserBuilder userBuilder) {
        this.userRepository = userRepository;
        this.userBuilder = userBuilder;
    }

    @Transactional
    @Override
    public Response save(CreateUserRequest request) {
        try {
            User user = userBuilder.buildForCreate(request);
            userRepository.save(user);
            return ResponseBuilder.success("User created Successfully");
        } catch (Exception exception) {
            LogUtil.exception("Failed while saving User");
            throw new SystemException();
        }
    }


    @Override
    public Response getAll() {
        try {
            List<User> users = userRepository.findAll();
            List<UserResponse> data = UserMapper.mapUsers(users);
            if (!data.isEmpty()) {
                return ResponseBuilder.success("Users fetched Successfully", data);
            }
            return ResponseBuilder.failure("Users not found");
        } catch (Exception exception) {
            LogUtil.exception("Failed while fetching Users");
            throw new SystemException();
        }
    }

    /*@Override
    public Response currentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserResponse data = UserMapper.mapUser((User) authentication.getPrincipal());
            return ResponseBuilder.success("User fetched Successfully", data);
        } catch (Exception exception) {
            LogUtil.exception("Failed while fetching User");
            throw new SystemException();
        }
    }
*/
    @Override
    public Response getUser(String email) {
        try {
            User user = userRepository
                    .findByEmailAddress(email)
                    .orElseThrow(() -> new DataNotFoundException(String.format("User not found with email %s", email)));
            UserResponse data = UserMapper.mapUser(user);
            return ResponseBuilder.success("User fetched Successfully", data);
        } catch (Exception exception) {
            LogUtil.exception("Failed while fetching User");
            throw new SystemException();
        }
    }
}
