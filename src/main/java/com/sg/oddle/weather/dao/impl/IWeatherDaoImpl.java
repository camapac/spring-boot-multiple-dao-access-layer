package com.sg.oddle.weather.dao.impl;

import org.springframework.stereotype.Repository;

import com.sg.oddle.weather.dao.IWeatherDao;
import com.sg.oddle.weather.entities.Weather;

@Repository
public class IWeatherDaoImpl extends IBaseDaoImpl<Weather, Long> implements IWeatherDao {
	
}
