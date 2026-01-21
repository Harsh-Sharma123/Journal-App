package com.harsh.journalApp.repository;

import com.harsh.journalApp.entities.ConfigJournalApp;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepo extends MongoRepository<ConfigJournalApp, ObjectId> {
}
