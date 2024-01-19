package com.foxminded.JavaStreamsAPI15.table.column.impl;

import java.util.Comparator;

import com.foxminded.JavaStreamsAPI15.model.Racer;
import com.foxminded.JavaStreamsAPI15.table.column.Column;

public class NameColumn implements Column {

	private final String TITLE = "Name";

	@Override
	public String getTitle() {
		return TITLE;
	}

	@Override
	public String getData(Racer racer) {
		if (racer != null) {
			return racer.getName();
		} else {
			return " ";
		}
	}

	@Override
	public Comparator<Racer> getComparator() {
		return Comparator.comparing(Racer::getName).reversed();
	}

}
