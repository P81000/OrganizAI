package com.organizai.app.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherApiResponse {
    private String cod;
    private int message;
    private List<WeatherData> list;


    public void setCod(String cod) {
        this.cod = cod;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public void setWeatherDataList(List<WeatherData> weatherDataList) {
        this.list = weatherDataList;
    }
}
