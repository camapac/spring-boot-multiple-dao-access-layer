package com.sg.oddle.weather.entities;

import java.io.Serializable;
import java.security.Timestamp;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="weather")
public class Weather implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8841234059683357342L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="city_id", nullable=false)
	private City city;
	
	@JsonManagedReference
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="weather",targetEntity=WeatherStatus.class)
	private List<WeatherStatus> weather;
	
	private Double temp;
	private Double temp_min;
	private Double temp_max;
	private Double pressure;
	private Double sea_level;
	private Double grnd_level;
	private Integer humidity;
	private Double temp_kf;
	private Integer clouds_all;
	private Double wind_speed;
	private Double wind_deg;
	private String sys_pod;
	private long dt;
	private Date dt_txt;
	private Timestamp created_date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public List<WeatherStatus> getWeather() {
		return weather;
	}
	public void setWeather(List<WeatherStatus> weather) {
		this.weather = weather;
	}
	public Double getTemp() {
		return temp;
	}
	public void setTemp(Double temp) {
		this.temp = temp;
	}
	public Double getTemp_min() {
		return temp_min;
	}
	public void setTemp_min(Double temp_min) {
		this.temp_min = temp_min;
	}
	public Double getTemp_max() {
		return temp_max;
	}
	public void setTemp_max(Double temp_max) {
		this.temp_max = temp_max;
	}
	public Double getPressure() {
		return pressure;
	}
	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}
	public Double getSea_level() {
		return sea_level;
	}
	public void setSea_level(Double sea_level) {
		this.sea_level = sea_level;
	}
	public Double getGrnd_level() {
		return grnd_level;
	}
	public void setGrnd_level(Double grnd_level) {
		this.grnd_level = grnd_level;
	}
	public Integer getHumidity() {
		return humidity;
	}
	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}
	public Double getTemp_kf() {
		return temp_kf;
	}
	public void setTemp_kf(Double temp_kf) {
		this.temp_kf = temp_kf;
	}
	public Integer getClouds_all() {
		return clouds_all;
	}
	public void setClouds_all(Integer clouds_all) {
		this.clouds_all = clouds_all;
	}
	public Double getWind_speed() {
		return wind_speed;
	}
	public void setWind_speed(Double wind_speed) {
		this.wind_speed = wind_speed;
	}
	public Double getWind_deg() {
		return wind_deg;
	}
	public void setWind_deg(Double wind_deg) {
		this.wind_deg = wind_deg;
	}
	public String getSys_pod() {
		return sys_pod;
	}
	public void setSys_pod(String sys_pod) {
		this.sys_pod = sys_pod;
	}
	public long getDt() {
		return dt;
	}
	public void setDt(long dt) {
		this.dt = dt;
	}
	public Date getDt_txt() {
		return dt_txt;
	}
	public void setDt_txt(Date dt_txt) {
		this.dt_txt = dt_txt;
	}
	public Timestamp getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}
	

}
