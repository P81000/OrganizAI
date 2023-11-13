package com.organizai.app.model.weather;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherInfo, Integer> {
    @Override
    WeatherInfo getById(Integer integer);

    //WeatherInfo GetWeatherByEventID(Integer idEvento);
}
