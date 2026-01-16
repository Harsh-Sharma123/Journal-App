package com.harsh.journalApp.controllers;

import com.harsh.journalApp.entities.UserEntity;
import com.harsh.journalApp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PutMapping
    public ResponseEntity<?> updateUserDetails(@RequestBody UserEntity user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity oldUser = userServices.findByUserName(username);
        oldUser.setUserName(user.getUserName());
        oldUser.setPassword(user.getPassword());
        userServices.createNewUser(oldUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public Boolean deleteUserByUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userServices.deleteUserByUsername(username);
        return true;
    }


}
