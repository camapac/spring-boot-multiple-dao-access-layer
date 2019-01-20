package com.sg.oddle.weather.task;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sg.oddle.weather.entities.Weather;
import com.sg.oddle.weather.event.Event;
import com.sg.oddle.weather.service.IWeatherService;

@Component
@Scope(scopeName="prototype")
public class PushWeatherLog implements Runnable {

	private final BlockingQueue<Event<Weather>> eventProcessQueue = new LinkedBlockingQueue<Event<Weather>>();

	@Autowired
	private IWeatherService iWeatherService;
	
	@Override
	public void run() {
		try {
			while (eventProcessQueue.size() > 0) {
				List<Weather> listWeatherInfo = eventProcessQueue.take().getData();
				iWeatherService.processWeather(listWeatherInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add event to queue
	 * @param event
	 */
	public void addEventQueue(Event<Weather> event){
		eventProcessQueue.add(event);
	}

}
