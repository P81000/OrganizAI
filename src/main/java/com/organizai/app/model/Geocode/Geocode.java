package com.organizai.app.model.Geocode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Dictionary;
import java.util.List;
import java.util.Map;


public class Geocode {
        String name;
        Map<String, String> localnames;
        double lat;
        double lon;
        String country;
        String state;

        @JsonProperty("name")
        public void setCityName(String cityName) {
            this.name = cityName;
        }

        @JsonProperty("country")
        public void setCountry(String country) {
            this.country = country;
        }

        @JsonProperty("state")
        public void setState(String state) {
            this.state = state;
        }

        @JsonProperty("lat")
        public void setLatitud(double latitud) {
            this.lat = latitud;
        }

        @JsonProperty("lon")
        public void setLongitude(double longitude) {
            this.lon = longitude;
        }

        @JsonProperty("name")
        public String getCityName() {
            return name;
        }

        @JsonProperty("local_names")
        public Map<String, String> getLocalnames() {
            return localnames;
        }

        @JsonProperty("lat")
        public double getLat() {
            return lat;
        }

        @JsonProperty("lon")
        public double getLon() {
            return lon;
        }

         @JsonProperty("country")
        public String getCountry() {
            return country;
        }

        @JsonProperty("state")
        public String getState() {
            return state;
        }
}



