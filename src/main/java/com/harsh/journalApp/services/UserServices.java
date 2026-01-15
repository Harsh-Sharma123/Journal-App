package com.harsh.journalApp.services;

import com.harsh.journalApp.entities.UserEntity;
import com.harsh.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserServices {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserEntity> getAllUsers(){
        return userRepo.findAll();
    }

    public Optional<UserEntity> getUserByID(ObjectId id){
        return userRepo.findById(id);
    }

    public void createNewUser(UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepo.save(user);
    }

    public void deleteUserByID(ObjectId id){
        userRepo.deleteById(id);
    }

    public void deleteUserByUsername(String username){
        userRepo.deleteByUserName(username);
    }

    public UserEntity findByUserName(String username){
        return userRepo.findByUserName(username);
    }

}
