package com.harsh.journalApp.services;

import com.harsh.journalApp.apiResponses.WeatherResponse;
import com.harsh.journalApp.cache.AppCache;
import com.harsh.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherServices {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private AppCache appcache;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        String finalApi = appcache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse refinedResponse = response.getBody();
        return refinedResponse;
    }
}
