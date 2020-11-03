package com.example.admin.controller;

import com.example.admin.service.UserService;
import com.example.common.domain.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody // 관리자가 user 관리할때
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ApiOperation(value = "ALL UserList (admin)", notes = "모든 사용자 조회")
    public List<User> AllUserList() {
        List<User> userList = userService.getUsers();
        return userList;
    }

}
