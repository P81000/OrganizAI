package com.organizai.app.model.weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.organizai.app.model.Geocode.Geocode;
import com.organizai.app.model.evento.Evento;
import com.organizai.app.model.weather.WeatherApiResponse;
import com.organizai.app.model.weather.WeatherData;
import com.organizai.app.model.weather.WeatherInfo;
import com.organizai.app.model.weather.WeatherRepository;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Service
public class WeatherServiceImpl implements WeatherService
 {

    @Value("${weather.api.key}")
    private String openWeatherMapApiKey;
    //private final WeatherRepository _weatherRepository;
//    @Autowired
//    public WeatherServiceImpl(WeatherRepository weatherRepository){
//        this._weatherRepository = weatherRepository;
//    }
   // @Override
    public WeatherInfo getWeatherJson(double latitude, double longitude) {
        String apiUrl = String.format("https://api.openweathermap.org/data/2.5/forecast?lat=%f&lon=%f&exclude=hourly,minutely&appid=%s",
                latitude, longitude, openWeatherMapApiKey);

        System.out.println("URL WEATHER: " + apiUrl);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, WeatherInfo.class);
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
        System.out.println("Returning null!");
        return null;
    }

     @Override
     public WeatherInfo SaveWeatherInfo(WeatherInfo weatherInfo) {
         //this._weatherRepository.save(weatherInfo);
         return null;
     }

     @Override
     public void processAndSaveWeatherInfo(WeatherApiResponse weatherApiResponse, Evento evento) { //TODO pegar apenas o periodo correto
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
         Date dataInicioEvento;
        try{
             dataInicioEvento = simpleDateFormat.parse(evento.getData_inicio());
        }catch(ParseException e){
            System.out.println(e.getMessage());
            return;
        }

        for(WeatherData weatherData : weatherApiResponse.getList()){
            Date weatherForecastDate = ForecastDateFormat(weatherData.getDt_txt());
            assert weatherForecastDate != null && dataInicioEvento != null;
            if (CompareDate(weatherForecastDate, dataInicioEvento)){
                WeatherInfo weatherInfo = new WeatherInfo(
                        evento,
                        weatherData.getPop(),
                        weatherData.getMain().get("humidity").asInt(),
                        weatherData.getMain().get("temp").asDouble(),
                        weatherData.getMain().get("temp_max").asDouble(),
                        weatherData.getMain().get("temp_min").asDouble(),
                        weatherData.getMain().get("feels_like").asDouble(),
                        weatherData.getWeather().get(0).get("description").asText()
                );
                //save weather info no repo
                System.out.println("Datas coincidem!!!");
            }
        }
     }

     public static Date ForecastDateFormat(String dateString){
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         try{
             return dateFormat.parse(dateString);
         }catch(ParseException e){
             System.out.println(e.getMessage());
         }
         return null;
     }

     public static boolean CompareDate(Date date1, Date date2){
        if(date1.getDate() == date2.getDate() &&
        date1.getMonth() == date2.getMonth() &&
        date1.getYear() == date2.getYear()){
            return true;
        }
        return false;
     }

//    @Override
//    public WeatherInfo GetWeatherByEventID(Integer idEvento) {
//       return _weatherRepository.GetWeatherByEventID(idEvento);
//    }

//    @Override
//    public WeatherInfo SaveWeatherInfo(WeatherInfo weatherInfo) {
//        System.out.println("Saving Weather info: " + weatherInfo.getMessage());
//        WeatherInfo savedWeatherInfo = _weatherRepository.save(weatherInfo);
//        return savedWeatherInfo;
//    }
}
