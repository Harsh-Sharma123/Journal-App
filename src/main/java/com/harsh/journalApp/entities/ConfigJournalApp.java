package com.harsh.journalApp.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "configs")
public class ConfigJournalApp {

    private String key;
    private String value;

}
