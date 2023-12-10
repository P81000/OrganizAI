package com.organizai.app.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

import java.util.List;
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {

    private JsonNode main;
    private JsonNode weather;
    @JsonProperty("pop")
    private double pop;
    @JsonProperty("dt_txt")
    private String dt_txt;

    public void setMain(JsonNode main) {
        this.main = main;
    }

    public void setWeather(JsonNode weather) {
        this.weather = weather;
    }

    public void setPop(double pop) {
        this.pop = pop;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }
}
