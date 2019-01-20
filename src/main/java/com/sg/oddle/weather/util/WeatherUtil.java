package com.sg.oddle.weather.util;

import java.util.List;
import java.util.stream.Collectors;

import com.sg.oddle.weather.domain.WeatherInfo;
import com.sg.oddle.weather.domain.WeatherStatusInfo;
import com.sg.oddle.weather.entities.City;
import com.sg.oddle.weather.entities.Weather;
import com.sg.oddle.weather.entities.WeatherStatus;

public class WeatherUtil {

	/**
	 * Convert weatherInfo to object entities
	 * @param info
	 * @param weather
	 * @return
	 */
	public static Weather weatherInfoToObject(WeatherInfo info,City city) {
		Weather weather = new Weather();
		if (info.getMain() != null) {
			weather.setTemp(info.getMain().getTemp());
			weather.setTemp_min(info.getMain().getTemp_min());
			weather.setTemp_max(info.getMain().getTemp_max());
			weather.setPressure(info.getMain().getPressure());
			weather.setSea_level(info.getMain().getSea_level());
			weather.setGrnd_level(info.getMain().getSea_level());
			weather.setHumidity(info.getMain().getHumidity());
			weather.setTemp_kf(info.getMain().getTemp_kf());
		}
			weather.setWeather(listWeatherInfoToObject(info.getWeather(),weather));
			
		if (info.getClouds() != null) 
			weather.setClouds_all(info.getClouds().getAll());
		if (info.getWind() != null) {
			weather.setWind_speed(info.getWind().getSpeed());
			weather.setWind_deg(info.getWind().getDeg());
		}
		if (info.getSys() != null)
			weather.setSys_pod(info.getSys().getPod());
		weather.setDt(info.getDt());
		weather.setDt_txt(info.getDt_txt());
		weather.setCity(city);
		return weather;
	}
	
	
	/**
	 * Convert listWeatherInfo to listObject
	 * @param listInfo
	 * @param weather
	 * @return
	 */
	public static List<WeatherStatus> listWeatherInfoToObject(List<WeatherStatusInfo> listInfo,Weather weather) {
		if (listInfo != null && listInfo.size() > 0) {
			return listInfo.stream().map( k -> WeatherUtil.statusInfoToObject(k,weather)).collect(Collectors.toList());
		}
		
		return null;
	}
	
	/**
	 * Convert WeatherStatus info to object
	 * @param info
	 * @param weather
	 * @return
	 */
	public static WeatherStatus statusInfoToObject(WeatherStatusInfo info,Weather weather) {
		WeatherStatus status = new WeatherStatus();
		status.setDescription(info.getDescription());
		status.setIcon(info.getIcon());
		status.setMain(info.getMain());
		status.setWeather(weather);
		return status;
	}
	

}
