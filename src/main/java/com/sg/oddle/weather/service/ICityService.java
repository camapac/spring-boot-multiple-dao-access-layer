package com.sg.oddle.weather.service;

import java.util.List;

import com.sg.oddle.weather.domain.CityInfo;
import com.sg.oddle.weather.entities.City;

public interface ICityService {
	
	public boolean addNewCity(CityInfo city);

	boolean addListCity(List<CityInfo> listCity);
	
	public boolean deleteAllData();

	List<String> getListCityName(String name);
	
	public long countData();

	List<City> getListCityByName(String name);

	void saveBulkCity(List<City> listCity);

}
