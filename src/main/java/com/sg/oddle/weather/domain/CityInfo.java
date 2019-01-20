package com.sg.oddle.weather.domain;

import com.sg.oddle.weather.entities.City;

public class CityInfo {

	private Integer id;
	private String name;
	private String country;
	private Coord coord;
	
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Coord getCoord() {
		return coord;
	}
	public void setCoord(Coord coord) {
		this.coord = coord;
	}
	
	public City toCity() {
		City city = new City();
		city.setName(getName());
		city.setCountryCode(getCountry());
		if (this.coord != null) {
			city.setCoordLat(this.coord.getLat());
			city.setCoordLon(this.coord.getLon());
		}
		return city;
	}

}
