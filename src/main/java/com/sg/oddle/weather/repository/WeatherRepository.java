package com.sg.oddle.weather.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sg.oddle.weather.entities.Weather;

@Repository
public interface WeatherRepository extends PagingAndSortingRepository<Weather, Integer> {

	@Query("SELECT count(*) FROM Weather w WHERE w.city.id = ?1 and w.dt = ?2")
	public long countCheckDataExisting(Integer cityId, long dt);
	
	
	public List<Weather> findTop20ByCity_IdOrderByDtDesc(Integer cityId);
	
	public Integer deleteByCity_IdAndDt(Integer cityId, Long dt);
}
