package com.sg.oddle.weather.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.oddle.weather.dao.ICityDao;
import com.sg.oddle.weather.domain.CityInfo;
import com.sg.oddle.weather.entities.City;
import com.sg.oddle.weather.repository.CityRepository;
import com.sg.oddle.weather.service.ICityService;

@Service
@Transactional
public class ICityServiceImpl implements ICityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ICityDao cityDao;
	
	
	
	@Override
	@Transactional
	public void saveBulkCity(List<City> listCity) {
		cityDao.saveBulkData(listCity, Optional.ofNullable(100));
	}
	
	
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
			cityDao.saveBulkData(listCity.parallelStream().map(k -> k.toCity()).collect(Collectors.toList()),Optional.ofNullable(100));
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
		return cityRepository.findByNameContaining(name);
		
	}
	
	@Override
	public List<String> getListCityName(String name) {
		List<City> listCity = cityRepository.findByNameContaining(name);
		if (listCity != null && listCity.size() > 0) {
			return listCity.stream().map(k -> k.getName()+","+k.getCountryCode()).collect(Collectors.toList());
		}
		
		return Collections.emptyList();
	}

}
