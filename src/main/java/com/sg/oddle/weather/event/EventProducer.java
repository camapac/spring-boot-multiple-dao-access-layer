package com.sg.oddle.weather.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventProducer {

	@Autowired
	private ApplicationEventPublisher producer;
	
	
	public void pushNotification(Event<?> event) {
		System.out.println("Begin push notification!....");
		producer.publishEvent(event);
	}
}
