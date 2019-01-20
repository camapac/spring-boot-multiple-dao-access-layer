package com.sg.oddle.weather.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="weather_status")
public class WeatherStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="weather_id", nullable=false)
	private Weather weather;
	
	private String main;
	private String description;
	private String icon;
	private Timestamp created_date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Weather getWeather() {
		return weather;
	}
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Timestamp getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}
	
}
