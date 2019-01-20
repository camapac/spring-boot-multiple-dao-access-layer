package com.sg.oddle.weather.event;

import java.util.List;

import com.sg.oddle.weather.util.EventsType;

public class Event<T> {

	private List<T> data;
	private EventsType type;
	
	
	public Event(List<T> data, EventsType type) {
		super();
		this.data = data;
		this.type = type;
	}


	public List<T> getData() {
		return data;
	}


	public void setData(List<T> data) {
		this.data = data;
	}


	public EventsType getType() {
		return type;
	}


	public void setType(EventsType type) {
		this.type = type;
	}
		
}
