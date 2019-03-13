# weather

Before you run project please run <b>weather.sql</b> query to create database and import database

To run project please run cmd

mvn spring-boot:run

And go to the link: http://localhost:8080/list  to access the page.

To build

mvn clean install

Project using:
Java 8, Spring boot 2, Spring data jpa, Hibernate, Mysql.

The project try to using both data dao layer: Spring data JPA, and Hibernate Custom. In the real project we can select one.



Business Logic

1 Search weather by CityID (List city get from our database) <br />
2 After we have the city call API from openWeatherApi to get forecast <br />
3 Get the forecast we filter data if the logs data not existing in the db we will process insert to db and return to client <br />
4 Delete the weather log data 


Please note: Some data deleted can be persist because after you search again the logic will save to database if check the data not existing db

In this step I using the Event/Listener task excuter to make better performace <br />
Using ControllAdvise for handler the level API exception. <br />

