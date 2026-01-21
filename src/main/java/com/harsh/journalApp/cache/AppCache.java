package com.harsh.journalApp.cache;

import com.harsh.journalApp.entities.ConfigJournalApp;
import com.harsh.journalApp.repository.ConfigRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys {
            WEATHER_API
    }

    @Autowired
    private ConfigRepo configRepo;

    public Map<String, String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigJournalApp> configs = configRepo.findAll();
        for(ConfigJournalApp config : configs){
            appCache.put(config.getKey(), config.getValue());
        }
    }

}
