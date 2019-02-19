package com.sg.oddle.weather.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sg.oddle.weather.entities.City;

@Repository
public interface CityRepository extends PagingAndSortingRepository<City, Integer> {

	  @Modifying
	  @Query(
	      value = "truncate table city",
	      nativeQuery = true
	  )
	  void truncateData();
	  
	  
	  List<City> findByNameContaining(final String name);
	  
	  City findByName(final String name);
}
