package com.sg.oddle.weather.api;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.oddle.weather.entities.Weather;
import com.sg.oddle.weather.exception.BadRequestException;
import com.sg.oddle.weather.exception.NotFoundException;
import com.sg.oddle.weather.service.IWeatherService;

@RestController
@RequestMapping("api/v1/weather")
public class WeatherRestApi {
	
	@Autowired
	private IWeatherService weatherService;

	@GetMapping(value="/{cityId}")
	ResponseEntity<List<Weather>> getListByName(@PathVariable("city") Integer cityId) {
		if (cityId != null) {
			List<Weather>  weathers = weatherService.getWeatherByCity(cityId);
			if (weathers != null && weathers.size() > 0) {
				return ResponseEntity.ok(weathers);
			} 
			throw new NotFoundException("No data found!");
		}
		 throw new BadRequestException("Invalid request params");
	}
	
	@DeleteMapping(value="/{cityId}/{dt}")
	ResponseEntity<?> deleteWeatherLog(@PathVariable("cityId") Integer cityId, @PathVariable("dt") Long dt){
		boolean result = weatherService.deleteWeatherLog(cityId,dt);
		return ResponseEntity.ok(result);
	}
}
