package com.harsh.journalApp.controllers;

import com.harsh.journalApp.entities.JournalEntity;
import com.harsh.journalApp.entities.UserEntity;
import com.harsh.journalApp.services.JournalServices;
import com.harsh.journalApp.services.UserServices;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journals")
public class JournalControllers {

    @Autowired
    private JournalServices journalServices;

    @Autowired
    private UserServices userServices;

    @PostMapping()
    public ResponseEntity<?> createNewJournalEntity(@RequestBody JournalEntity journal){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            journalServices.saveJournalEntity(journal, authentication.getName());
            return new ResponseEntity<>(journal, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllJournals(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity user = userServices.findByUserName(username);
        List<JournalEntity> journals = user.getJournalEntities();
        if(journals.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(journals, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntity> getJournalByID(@PathVariable ObjectId id){
        Optional<JournalEntity> journalEntity = journalServices.getJournalByID(id);
        if(journalEntity.isPresent()){
            return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{username}/{id}")
    public ResponseEntity<?> deleteJournalByID(@PathVariable ObjectId id, @PathVariable String username){
        journalServices.deleteJournalByID(id, username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{username}/{id}")
    public ResponseEntity<?> updateJournalByID(@PathVariable ObjectId id, @RequestBody JournalEntity newJournal, @PathVariable String username){

        JournalEntity oldJournal = journalServices.getJournalByID(id).orElse(null);
        if(oldJournal != null){
            oldJournal.setTitle(newJournal.getTitle()!=null && !newJournal.getTitle().equals("") ? newJournal.getTitle() : oldJournal.getTitle());
            oldJournal.setDescription(newJournal.getDescription()!=null && !newJournal.getDescription().equals("") ? newJournal.getDescription() : oldJournal.getDescription());
        }
//        journalServices.saveJournalEntity(oldJournal);
        return new ResponseEntity<>(newJournal, HttpStatus.OK);
    }
}
