package com.organizai.app.model.weather.service;

import com.organizai.app.model.Geocode.Geocode;
import com.organizai.app.model.evento.Evento;
import com.organizai.app.model.weather.WeatherApiResponse;
import com.organizai.app.model.weather.WeatherInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;

public interface WeatherService{

    //WeatherInfo GetWeatherByEventID(Integer idEvento);

    WeatherInfo SaveWeatherInfo(WeatherInfo weatherInfo);

    public WeatherInfo processAndSaveWeatherInfo(WeatherApiResponse weatherApiResponse, Evento evento);
}
