package com.foxminded.JavaStreamsAPI15.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtils {

	private StringUtils() {
	}

	public static String getID(String inputString) {
		if (inputString == null || inputString.length() < 3) {
			return "";
		}
		String id = inputString.substring(0, 3);
		return id;
	}

	public static String getName(String inputString) {
		if (inputString == null || inputString.indexOf('_') == -1 || inputString.lastIndexOf('_') == -1) {
			return "";
		}
		String name;
		int firstUnderscorePosition = inputString.indexOf('_');
		int lastUnderscorePosition = inputString.lastIndexOf('_');
		name = inputString.substring(firstUnderscorePosition + 1, lastUnderscorePosition);

		return name;
	}

	public static String getTeam(String inputString) {
		if (inputString == null || inputString.lastIndexOf('_') == -1) {
			return "";
		}
		String team;
		int lastUnderscorePosition = inputString.lastIndexOf('_');
		team = inputString.substring(lastUnderscorePosition + 1);
		return team;
	}

	public static LocalDateTime getDateTime(String inputString) {
		if (inputString == null || inputString.isEmpty() || inputString.length() < "yyyy-MM-dd HH:mm:ss.SSS".length()) {
			return LocalDateTime.MIN;
		}
		String dateTimeString = inputString.substring(3).replace('_', ' ');
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		return LocalDateTime.parse(dateTimeString, formatter);
	}

}
