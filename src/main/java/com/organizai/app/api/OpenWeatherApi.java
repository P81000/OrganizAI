package com.organizai.app.api;

import com.organizai.app.model.Geocode.Geocode;
import com.organizai.app.model.weather.WeatherApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class OpenWeatherApi {
    @Value("${weather.api.key}")
    private String _apiKey;

    public OpenWeatherApi(String apiKey){
        this._apiKey = apiKey;
    }

    public Geocode GetGeocodeCoordinates(String city, String State){ //TODO adicionar filtro para o estado requisitado.
        Geocode geocodeParams = null;
        try{
            String geocodeUrlApi =
                    String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=5&appid=%s", city, _apiKey);
            System.out.println("URL GEOCODE: " + geocodeUrlApi);
            RestTemplate restTemplate = new RestTemplate();
            var response = restTemplate.getForObject(geocodeUrlApi, Geocode[].class);
            assert response != null;
            geocodeParams = response[0];
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Returning null!");
        return geocodeParams;
    }

    public static Geocode StateFilter(Geocode[] response, String state){
        for (Geocode geocode:response) {
            if(Objects.equals(geocode.getState(), state)) return geocode;
        }
        return response[0];
    }

    public WeatherApiResponse GetOpenWeather5DayForecast(double lat, double lon){
        WeatherApiResponse weatherApiResponse = null;
        try{
            String apiUrl = String.format("https://api.openweathermap.org/data/2.5/forecast?lat=%f&lon=%f&exclude=hourly,minutely&appid=%s",
                    lat, lon, _apiKey);
            System.out.println("URL WEATHER: " + apiUrl);

            RestTemplate restTemplate = new RestTemplate();
            weatherApiResponse = restTemplate.getForObject(apiUrl, WeatherApiResponse.class);

            System.out.println(weatherApiResponse);
            System.out.println(weatherApiResponse.getList());
            return weatherApiResponse;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("WeatherAPIResponse: Returning null");
        return weatherApiResponse;
    }
}
