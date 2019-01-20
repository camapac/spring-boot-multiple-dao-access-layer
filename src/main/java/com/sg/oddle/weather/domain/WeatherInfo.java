package com.sg.oddle.weather.domain;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class WeatherInfo {
	
	private long dt;
	private WeatherMain main;
	private List<WeatherStatusInfo> weather;
	private WeatherCloud clouds;
	private WeatherWind wind;
	private WeatherSys sys;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dt_txt;
	public long getDt() {
		return dt;
	}
	public void setDt(long dt) {
		this.dt = dt;
	}
	public WeatherMain getMain() {
		return main;
	}
	public void setMain(WeatherMain main) {
		this.main = main;
	}
	public List<WeatherStatusInfo> getWeather() {
		return weather;
	}
	public void setWeather(List<WeatherStatusInfo> weather) {
		this.weather = weather;
	}
	public WeatherCloud getClouds() {
		return clouds;
	}
	public void setClouds(WeatherCloud clouds) {
		this.clouds = clouds;
	}
	public WeatherWind getWind() {
		return wind;
	}
	public void setWind(WeatherWind wind) {
		this.wind = wind;
	}
	public WeatherSys getSys() {
		return sys;
	}
	public void setSys(WeatherSys sys) {
		this.sys = sys;
	}
	public Date getDt_txt() {
		return dt_txt;
	}
	public void setDt_txt(Date dt_txt) {
		this.dt_txt = dt_txt;
	}

}
