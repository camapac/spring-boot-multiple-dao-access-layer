package com.sg.oddle.weather.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.oddle.weather.domain.CityInfo;
import com.sg.oddle.weather.entities.City;
import com.sg.oddle.weather.repository.CityRepository;
import com.sg.oddle.weather.service.ICityService;

@Service
@Transactional
public class ICityServiceImpl implements ICityService {

	@Autowired
	private CityRepository cityRepository;
	
	
	
	@Override
	public boolean addNewCity(CityInfo city) {
		if (city != null) {
			cityRepository.save(city.toCity());
			return true;
		}
		return false;
	}
	
	
	@Override
	public boolean addListCity(List<CityInfo> listCity) {
		if (listCity != null && listCity.size() > 0) {
			cityRepository.saveAll(listCity.parallelStream().map(k -> k.toCity()).collect(Collectors.toList()));
			return true;
		}
		return false;
	}


	@Override
	public boolean deleteAllData() {
		cityRepository.truncateData();
		return true;
	}
	
	
	
	@Override
	public long countData() {
		return cityRepository.count();
	}

	@Override
	public List<City> getListCityByName(String name) {
		return cityRepository.findByNameLike(name);
		
	}
	
	@Override
	public List<String> getListCityName(String name) {
		List<City> listCity = cityRepository.findByNameLike(name);
		if (listCity != null && listCity.size() > 0) {
			return listCity.stream().map(k -> k.getName()+","+k.getCountryCode()).collect(Collectors.toList());
		}
		
		return Collections.emptyList();
	}

}
