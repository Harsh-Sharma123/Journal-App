package com.harsh.journalApp.controllers;

import com.harsh.journalApp.entities.UserEntity;
import com.harsh.journalApp.services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServices userServices;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        logger.debug("public/users/ called !");
        List<UserEntity> users = userServices.getAllUsers();
        if(!users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
