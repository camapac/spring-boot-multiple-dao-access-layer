package com.sg.oddle.weather.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sg.oddle.weather.entities.Weather;

@Repository
public interface WeatherRepository extends PagingAndSortingRepository<Weather, Long> {

	@Query("SELECT count(*) FROM Weather w WHERE w.city.id = ?1 and w.dt = ?2")
	public long countCheckDataExisting(Integer cityId, long dt);
}
