package com.example.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.api.apiResponse.WeatherResponse;
import com.example.api.cache.AppCache;

@Component
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    // private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
    
    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;
    
    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_"+ city, WeatherResponse.class);
        if (weatherResponse != null) {
            return weatherResponse;            
        } else {
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace("<city>", city).replace("<apiKey>", apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null) {
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }
    }
}