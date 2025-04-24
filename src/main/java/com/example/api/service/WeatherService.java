package com.example.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.api.apiResponse.WeatherResponse;

@Component
public class WeatherService {

    private final String apiKey = "916010e9076825bb62f77df467b9b007";
    private final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
    
    @Autowired
    private RestTemplate restTemplate;
    
    public WeatherResponse getWeather(String city) {
        String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
