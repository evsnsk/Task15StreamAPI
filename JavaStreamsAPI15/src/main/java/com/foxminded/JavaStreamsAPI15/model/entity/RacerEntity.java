package com.foxminded.JavaStreamsAPI15.model.entity;

public class RacerEntity {

	private final String id;
	private final String name;
	private final String team;

	public RacerEntity(String id, String name, String team) {
		this.id = id;
		this.name = name;
		this.team = team;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTeam() {
		return team;
	}

	@Override
	public String toString() {
		return "id: " + id + " name: " + name + " team: " + team + "\n";
	}

}
