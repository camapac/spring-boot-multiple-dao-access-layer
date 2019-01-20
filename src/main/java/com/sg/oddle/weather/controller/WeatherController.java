package com.sg.oddle.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sg.oddle.weather.entities.Weather;
import com.sg.oddle.weather.service.IWeatherService;

@Controller
public class WeatherController {
	
	@Autowired
	private IWeatherService weatherService; 
	
	
	@GetMapping("/list")
	public String weather() {
		try {
			//List<Weather> listWeather =  weatherService.getWeatherByCity(1123);
//			if (listWeather != null && listWeather.size() > 0) {
//				System.out.println("Have data ...");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
}
