package com.harsh.journalApp.repository;

import com.harsh.journalApp.entities.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepository extends MongoRepository<JournalEntity, ObjectId> {
}
