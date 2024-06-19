package com.acciojob.BookMyShowMAY.Controller;

import com.acciojob.BookMyShowMAY.Requests.AddUserRequest;
import com.acciojob.BookMyShowMAY.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public String addUser(@RequestBody AddUserRequest addUserRequest){

        return userService.addUser(addUserRequest);
    }
}
