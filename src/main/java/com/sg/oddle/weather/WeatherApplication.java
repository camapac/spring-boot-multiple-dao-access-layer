package com.sg.oddle.weather;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sg.oddle.weather.config.WeatherConfiguration;
import com.sg.oddle.weather.domain.CityInfo;
import com.sg.oddle.weather.service.ICityService;

@SpringBootApplication
@Import(WeatherConfiguration.class)
public class WeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(ICityService cityService) {
		return  args -> {
			if (cityService.countData() <= 0) {
				ObjectMapper objectMapper = new ObjectMapper();
				TypeReference<List<CityInfo>> typeReference = new TypeReference<List<CityInfo>>(){};
				InputStream inputStream = TypeReference.class.getResourceAsStream("/json/city.list.json");
				try {
					List<CityInfo> listCity = objectMapper.readValue(inputStream, typeReference);
					if (listCity != null && listCity.size() > 0) {
						System.out.println("Begin import data ...");
						cityService.deleteAllData();
						System.out.println("Try truncate old data ...");
						System.out.println("Importing ...");
						long t = System.currentTimeMillis();
						System.out.println("TimeStart: "+t);
						cityService.addListCity(listCity);
						System.out.println("Done");
						long t2 = System.currentTimeMillis();
						long total = t2 - t;
						System.out.println("Total second: "+total/100);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}

}

