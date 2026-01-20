package com.harsh.journalApp.services;

import com.harsh.journalApp.apiResponses.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherServices {

    private static final String apiKey = "bf6b26f6cc725625abb3cfa748b29410";
    private static final String apiUrl = "http://api.weatherstack.com/current?access_key="+apiKey+"&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalApi = apiUrl.replace("CITY", city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse refinedResponse = response.getBody();
        return refinedResponse;
    }
}
