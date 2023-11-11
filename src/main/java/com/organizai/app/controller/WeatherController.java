package com.organizai.app.controller;

import com.organizai.app.model.weather.WeatherInfo;
import com.organizai.app.model.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class WeatherController {

    @Value("${weather.api.key}")
    private String weatherApiKey;

    private WeatherService _weatherService;

    @GetMapping("/getWeather")
    @ResponseBody
    public ResponseEntity<WeatherInfo> getWeatherInfo(@RequestParam String city, String country){
        String geocodeUrl =
                String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=5&appid=%s", city, weatherApiKey);

        return null;
    }

}
