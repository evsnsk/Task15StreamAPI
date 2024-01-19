package com.foxminded.JavaStreamsAPI15.model.entity;

import java.time.LocalDateTime;

public class LapEntity {

	private final String id;
	private final LocalDateTime time;

	public LapEntity(String id, LocalDateTime time) {
		this.id = id;
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "id: " + id + "   time: " + time + "\n";
	}

}
