package com.harsh.journalApp.apiResponses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {

    private Current current;

    @Getter
    @Setter
    public class Current{
        private int temperature;
        @JsonProperty("weather_description")
        private List<String> weatherDescriptions;
        private int feelslike;
    }
}
