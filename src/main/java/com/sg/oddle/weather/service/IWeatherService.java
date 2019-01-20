package com.sg.oddle.weather.service;

import java.util.List;

import com.sg.oddle.weather.entities.Weather;

public interface IWeatherService {

	void processWeather(List<Weather> listWeatherInfo);

	List<Weather> getWeatherByCity(Integer cityId);

	void getWeatherByCity(String name);

	boolean deleteWeatherLog(Integer cityId, Long dt);

}
