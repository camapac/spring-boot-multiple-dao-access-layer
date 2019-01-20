package com.sg.oddle.weather.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.sg.oddle.weather.entities.Weather;
import com.sg.oddle.weather.task.PushWeatherLog;
import com.sg.oddle.weather.util.EventsType;

@Component
public class EventConsumer {

	@Autowired
	@Qualifier("myThreadPoolExecutor")
	private TaskExecutor taskExecutor; 
	
	
	@Autowired
	private PushWeatherLog pushWeatherTask;
	
	@EventListener
	void notificationToClient(Event<?> event) {
		if (event != null) {
			processEvent(event);
		}
	}

	@SuppressWarnings("unchecked")
	private void processEvent(Event<?> event) {
		EventsType type = event.getType();
		
		switch (type) {
		case PUSH_DATA_TO_DB:
			System.out.println("Start proccess insert data to database");
			pushWeatherTask.addEventQueue((Event<Weather>) event);
			taskExecutor.execute(pushWeatherTask);
			break;

		default:
			break;
		}
	}
}
