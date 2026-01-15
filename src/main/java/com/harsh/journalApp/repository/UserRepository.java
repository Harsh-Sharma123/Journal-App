package com.harsh.journalApp.repository;

import com.harsh.journalApp.entities.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {

    UserEntity findByUserName(String userName);

    void deleteByUserName(String userName);
}
