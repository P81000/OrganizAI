package com.organizai.app.model.weather.service;

import com.organizai.app.model.Geocode.Geocode;
import com.organizai.app.model.weather.WeatherInfo;
import com.organizai.app.model.weather.WeatherRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Getter
@Service
public class WeatherServiceImpl implements WeatherService{

    @Value("${weather.api.key}")
    private String openWeatherMapApiKey;
    private final WeatherRepository _weatherRepository;
    @Autowired
    public WeatherServiceImpl(WeatherRepository weatherRepository){
        this._weatherRepository = weatherRepository;
    }
    @Override
    public String getWeatherJson(double latitude, double longitude) {
        String apiUrl = String.format("https://api.openweathermap.org/data/2.5/forecast?lat=%f&lon=%f&exclude=hourly,minutely&appid=%s",
                latitude, longitude, openWeatherMapApiKey);

        System.out.println("URL WEATHER: " + apiUrl);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, String.class);
    }

    public Geocode getGeoCodeObject(String localizacao){
        try{
            String geocodeUrlApi =
                    String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=5&appid=%s", localizacao, getOpenWeatherMapApiKey());
            System.out.println("URL GEOCODE: " + geocodeUrlApi);
            RestTemplate restTemplate = new RestTemplate();
            var response = restTemplate.getForObject(geocodeUrlApi, Geocode[].class);
            assert response != null;
            return response[0];
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

//    @Override
//    public WeatherInfo GetWeatherByEventID(Integer idEvento) {
//       return _weatherRepository.GetWeatherByEventID(idEvento);
//    }

    @Override
    public WeatherInfo SaveWeatherInfo(WeatherInfo weatherInfo) {
        System.out.println("Saving Weather info: " + weatherInfo.getMessage());
        WeatherInfo savedWeatherInfo = _weatherRepository.save(weatherInfo);
        return savedWeatherInfo;
    }
}
