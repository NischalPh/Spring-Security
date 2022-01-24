package com.nischal.security.controller;

import com.nischal.security.dto.Response;
import com.nischal.security.request.CreateUserRequest;
import com.nischal.security.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping(ApiConstant.PROFILE)
//    public Response currentUser() {
//        return userService.currentUser();
//    }

    @PostMapping("user")
    // @PreAuthorize(RoleAuthorityConstant.CREATE_USER)
    public Response create(@RequestBody @Valid CreateUserRequest request) {
        return userService.save(request);
    }

    @GetMapping("users")
    //@PreAuthorize(RoleAuthorityConstant.VIEW_USER)
    public Response users() {
        return userService.getAll();
    }

    @GetMapping("user")
    // @PreAuthorize(RoleAuthorityConstant.VIEW_USER)
    public Response findUserByEmail(@RequestParam("email") String email) {
        return userService.getUser(email);
    }
}
