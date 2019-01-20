# weather

To run project please run cmd

mvn spring-boot:run

To build

mvn clean install

Project using:
Java 8, Spring boot 2, Spring data jpa, Hibernate, Mysql.

The project haven't finish because i just do the project yesterday night due workload at the company 
The project try to using both data dao layer: Spring data JPA, and Hibernate Custom. In the real project we can select one.



Business Logic

1 Search weather by CityID (List city get from our database)
2 After we have the city call API from openWeatherApi to get forecast
3 Get the forecast we filter data if the logs data not existing in the db we will process insert to db and return to client
In this step I using the Event/Listener task excuter to make better performace 
Using ControllAdvise for handler the level API exception.

To implement preformcace in the future we can using caching solution and load balancer.

Not have the time to implement the testing. 