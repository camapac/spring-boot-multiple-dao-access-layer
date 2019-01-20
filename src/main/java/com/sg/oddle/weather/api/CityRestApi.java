package com.sg.oddle.weather.api;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.oddle.weather.entities.City;
import com.sg.oddle.weather.exception.NotFoundException;
import com.sg.oddle.weather.service.ICityService;

@RestController
@RequestMapping("api/v1/city")
public class CityRestApi {
	
	@Autowired
	private ICityService cityService;

	@GetMapping(value="/{name}")
	ResponseEntity<?> getListByName(@PathVariable("name") String name) {
		System.out.println("Find city by name "+name);
		List<City> listCity = cityService.getListCityByName(name);
		if (listCity != null && listCity.size() > 0) {
			return ResponseEntity.ok(listCity);
		} 
		throw new NotFoundException("No data found!");

	}
}
