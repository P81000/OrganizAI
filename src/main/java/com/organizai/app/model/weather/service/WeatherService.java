package com.organizai.app.model.weather.service;

import com.organizai.app.model.Geocode.Geocode;
import com.organizai.app.model.weather.WeatherInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

public interface WeatherService {

    public String getWeatherJson(double latitude, double longitude);

    public Geocode getGeoCodeObject(String localizacao);

    //WeatherInfo GetWeatherByEventID(Integer idEvento);

    WeatherInfo SaveWeatherInfo(WeatherInfo weatherInfo);
}
