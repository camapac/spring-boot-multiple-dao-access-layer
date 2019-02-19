package com.sg.oddle.weather.service.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sg.oddle.weather.dao.IWeatherDao;
import com.sg.oddle.weather.domain.WeatherApiResponse;
import com.sg.oddle.weather.domain.WeatherInfo;
import com.sg.oddle.weather.entities.City;
import com.sg.oddle.weather.entities.Weather;
import com.sg.oddle.weather.event.Event;
import com.sg.oddle.weather.event.EventProducer;
import com.sg.oddle.weather.repository.CityRepository;
import com.sg.oddle.weather.repository.WeatherRepository;
import com.sg.oddle.weather.service.IWeatherService;
import com.sg.oddle.weather.util.EventsType;
import com.sg.oddle.weather.util.RestTemplateHelper;
import com.sg.oddle.weather.util.WeatherUtil;

@Service
@Transactional
public class IWeatherServiceImpl implements IWeatherService {
	
	@Value("${openweather.api.get.weather}")
	private String get_weather_url;

	@Autowired
	private EventProducer eventProducer;

	@Autowired
	private IWeatherDao weatherDao;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private WeatherRepository weatherRepository;

	@Override
	@Transactional
	public void processWeather(List<Weather> listWeather) {
		weatherDao.saveBulkData(listWeather, Optional.ofNullable(100));
	}
	
	
	
	@Override
	public void getWeatherByCity(String name) {
		
	}

	@Transactional
	@Override
	public boolean deleteWeatherLog(Integer cityId, Long dt) {
		Integer weather = weatherRepository.deleteByCity_IdAndDt(cityId, dt);
		if (weather != null) {
			return true;
		}
		return false;
	}



	@Transactional
	@Override
	public List<Weather> getWeatherByCity(Integer cityId) {

		Optional<City> optionalCity = cityRepository.findById(cityId);
		if (optionalCity.isPresent()) {
			City city = optionalCity.get();
			
			
			try {
				WeatherApiResponse weatherResponse = getWeatherResponse(city.getName());
				if (weatherResponse != null) {
					List<WeatherInfo> listWeatherInfo = weatherResponse.getList();
					if (listWeatherInfo != null && listWeatherInfo.size() > 0) {

						System.out.println("Found data from openWeatherAPI");
						List<Weather> weathers = listWeatherInfo.parallelStream().filter(existInDatabase(city.getId()))
								.map(k -> {

									return WeatherUtil.weatherInfoToObject(k, city);

								}).collect(Collectors.toList());
						
						if (weathers != null && weathers.size() > 0) {
							Event<Weather> event = new Event<>(weathers, EventsType.PUSH_DATA_TO_DB);
							eventProducer.pushNotification(event);
							return weathers;
						} 

					}
				}
			} catch (Exception e) {
				//Too be implement
			}
			//Try get the weather from database and return the data.
			return getWeatherByCity(city);

		}

		return null;
	}
	
	
	private List<Weather> getWeatherByCity(City city) {
		return weatherRepository.findTop20ByCity_IdOrderByDtDesc(city.getId());
	}



	public Predicate<WeatherInfo> existInDatabase(Integer cityId) {
		return p -> {
			long count = weatherRepository.countCheckDataExisting(cityId, p.getDt());
			if (count <= 0)
				return true;
			return false;
		};
	}

	/**
	 * Call openWeatherAPI get weather
	 * 
	 * @param name
	 * @return
	 */
	private WeatherApiResponse getWeatherResponse(String name) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<>();
		params.put("name", name);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		URI uri = RestTemplateHelper.buildUriGetParam(get_weather_url, params);
		System.out.println(uri.toString());
		ResponseEntity<WeatherApiResponse> result = restTemplate.exchange(uri, HttpMethod.GET, entity,
				WeatherApiResponse.class);

		if (result != null)
			return result.getBody();
		return null;
	}
}
