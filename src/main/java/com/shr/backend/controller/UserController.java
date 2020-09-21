package com.shr.backend.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shr.backend.entity.*;
import com.shr.backend.service.AuthorityService;
import com.shr.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthorityService authorityService;

    @PostMapping(value = "/register")
    @ResponseBody
    @CrossOrigin
    public String register(@RequestBody User user) {
        userService.createCustomer(user);
        return "Reg finished";
    }

    @PostMapping(value = "/isDuplicate")
    @ResponseBody
    @CrossOrigin
    public Boolean isUsernameDuplicate(@RequestBody User user) {
        return userService.isNameDuplicate(user.getUserName());
    }

    @PostMapping(value = "/login")
    @ResponseBody
    @CrossOrigin
    public String loginValidate(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        if(userService.validate(user.getUserName(), user.getPassword())) {
            User user1 = userService.findUserByName(user.getUserName());
            Authority authority = authorityService.getAuthorityByUsername(user.getUserName());
            jsonObject.put("isValid", true);
            jsonObject.put("isActive", user1.getActive());
            jsonObject.put("authority", authority.getName());
        }
        else {
            jsonObject.put("isValid", false);
        }
        return jsonObject.toJSONString();
    }

    @GetMapping(value = "/allUser")
    @ResponseBody
    @CrossOrigin
    public String fetchAllUser() {
        JSONArray jsonArray = new JSONArray();
        List<User> userList = userService.getAllUser();
        for(User user : userList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", user.getUserId());
            jsonObject.put("userName", user.getUserName());
            jsonObject.put("email", user.getEmail());
            Authority authority = authorityService.getAuthorityByUsername(user.getUserName());
            jsonObject.put("authority", authority.getName());
            jsonObject.put("isActive", user.getActive());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @PutMapping(value = "/able")
    @ResponseBody
    @CrossOrigin
    public void disableUser(@RequestParam("userId") Integer Id,
                            @RequestParam("active") Boolean active) {
        userService.disableOrEnableUser(Id, active);
    }
}
