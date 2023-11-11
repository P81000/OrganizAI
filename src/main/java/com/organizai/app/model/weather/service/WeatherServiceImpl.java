package com.organizai.app.model.weather.service;

import com.organizai.app.model.Geocode.Geocode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class WeatherServiceImpl implements WeatherService{

    @Value("${weather.api.key}")
    private String openWeatherMapApiKey;

    public String getOpenWeatherMapApiKey() {
        return openWeatherMapApiKey;
    }
    public String getWeatherJson(double latitude, double longitude) {
        String apiUrl = String.format("https://api.openweathermap.org/data/2.5/onecall?lat=%f&lon=%f&exclude=hourly,minutely&appid=%s",
                latitude, longitude, openWeatherMapApiKey);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, String.class);
    }

    public Geocode getGeoCodeObject(String localizacao){
        String geocodeUrlApi =
                String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=5&appid=%s", localizacao, getOpenWeatherMapApiKey());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(geocodeUrlApi, Geocode.class);
    }
}
