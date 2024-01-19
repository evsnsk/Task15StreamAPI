package com.foxminded.JavaStreamsAPI15.model;

import java.util.List;

public class Racer {

	private final String name;
	private final String team;
	private final List<Lap> laps;

	public Racer(String name, String team, List<Lap> laps) {
		this.name = name;
		this.team = team;
		this.laps = laps;
	}

	public String getName() {
		return name;
	}

	public String getTeam() {
		return team;
	}

	public List<Lap> getLaps() {
		return laps;
	}

	@Override
	public String toString() {
		return "name: " + name + " team: " + team + " laps:" + laps.toString() + "\n";
	}

}
