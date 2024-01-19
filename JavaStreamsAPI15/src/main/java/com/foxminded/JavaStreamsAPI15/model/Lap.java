package com.foxminded.JavaStreamsAPI15.model;

import java.time.LocalDateTime;

public class Lap {

	private final LocalDateTime start;
	private final LocalDateTime finish;

	public Lap(LocalDateTime start, LocalDateTime finish) {
		this.start = start;
		this.finish = finish;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public LocalDateTime getFinish() {
		return finish;
	}

	@Override
	public String toString() {
		return "start: " + start + " finish: " + finish;
	}

}
