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
    private final WeatherRepository _weatherRepository;
    @Autowired
    public WeatherServiceImpl(WeatherRepository weatherRepository){
        this._weatherRepository = weatherRepository;
    }

     @Override
     public WeatherInfo SaveWeatherInfo(WeatherInfo weatherInfo) {
        WeatherInfo weatherInfo1 = this._weatherRepository.save(weatherInfo);
        return weatherInfo1;
     }

     public WeatherInfo processAndSaveWeatherInfo(WeatherApiResponse weatherApiResponse, Evento evento) { //TODO pegar apenas o periodo correto
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
         Date dataInicioEvento;
        try{
             dataInicioEvento = simpleDateFormat.parse(evento.getData_inicio());
        }catch(ParseException e){
            System.out.println(e.getMessage());
            return null;
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
                evento.set_info_clima(weatherInfo);
                System.out.println(weatherInfo.getDescricao());
                System.out.println("Datas coincidem!!!");
                return weatherInfo;
            }
        }
        //se nao há data correspondente, retorna o primeiro forecast
        WeatherData weatherData = weatherApiResponse.getList().get(0);
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
        evento.set_info_clima(weatherInfo);
        return weatherInfo;
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

}
