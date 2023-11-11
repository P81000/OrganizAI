package com.organizai.app.model.weather;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherInfo, Integer> {
    WeatherInfo GetWeatherByEventID(Integer idEvento);
}
