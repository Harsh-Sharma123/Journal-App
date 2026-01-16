package com.harsh.journalApp.services;

import com.harsh.journalApp.entities.JournalEntity;
import com.harsh.journalApp.entities.UserEntity;
import com.harsh.journalApp.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalServices {

    @Autowired
    private JournalRepository journalRepo;

    @Autowired
    private UserServices userServices;

    @Transactional
    public void saveJournalEntity(JournalEntity journal, String username){
        try {
            UserEntity user = userServices.findByUserName(username);
            journal.setDate(LocalDateTime.now());
            JournalEntity savedJournal = journalRepo.save(journal);
            user.getJournalEntities().add(savedJournal);
//            user.setUserName(null);
            userServices.saveJournalToUser(user);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void updateJournalEntity(JournalEntity journal){
        journalRepo.save(journal);
    }

    public List<JournalEntity> getAllJournals(){
        return journalRepo.findAll();
    }

    @Transactional
    public void deleteJournalByID(ObjectId id, String username){
        try{
            UserEntity user = userServices.findByUserName(username);
            Boolean removed = user.getJournalEntities().removeIf(x -> x.getId().equals(id));
            if(removed){
                userServices.saveJournalToUser(user);
                journalRepo.deleteById(id);
            }
        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured while deleting the entry" + e);
        }


    }

    public Optional<JournalEntity> getJournalByID(ObjectId id){
        return journalRepo.findById(id);
    }


}
