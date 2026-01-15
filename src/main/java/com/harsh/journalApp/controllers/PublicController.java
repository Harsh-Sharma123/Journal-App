package com.harsh.journalApp.controllers;

import com.harsh.journalApp.entities.UserEntity;
import com.harsh.journalApp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserServices userServices;

    @GetMapping("health-check")
    public String healthCheck(){
        return "Ok";
    }

    @PostMapping("/user")
    public Boolean createuser(@RequestBody UserEntity user){
        userServices.createNewUser(user);
        return true;
    }

}
