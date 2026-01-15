package com.harsh.journalApp.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entities")
@Data
public class JournalEntity {

    @Id
    private ObjectId id;
    private String title;
    private String description;
    private LocalDateTime date;

}
