package com.sg.oddle.weather.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class City implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4415678726092240011L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id",unique = true, nullable = false)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="country_code")
	private String countryCode;
	
	@Column(name="coord_lon")
	private Double coordLon;
	
	@Column(name="coord_lat")
	private Double coordLat;
	
	
//	@OneToMany(mappedBy="city",fetch=FetchType.LAZY,targetEntity=Weather.class)
//	private List<Weather> weather;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Double getCoordLon() {
		return coordLon;
	}

	public void setCoordLon(Double coordLon) {
		this.coordLon = coordLon;
	}

	public Double getCoordLat() {
		return coordLat;
	}

	public void setCoordLat(Double coordLat) {
		this.coordLat = coordLat;
	}

//	public List<Weather> getWeather() {
//		return weather;
//	}
//
//	public void setWeather(List<Weather> weather) {
//		this.weather = weather;
//	}
		
	
}
