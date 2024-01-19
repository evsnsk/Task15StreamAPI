package com.foxminded.JavaStreamsAPI15.table.column.impl;

import java.util.Comparator;

import com.foxminded.JavaStreamsAPI15.model.Racer;
import com.foxminded.JavaStreamsAPI15.table.column.Column;
import com.foxminded.JavaStreamsAPI15.utils.RacerUtils;

public class LapCountColumn implements Column {

	private final String TITLE = "Lap Count";

	@Override
	public String getTitle() {
		return TITLE;
	}

	@Override
	public String getData(Racer racer) {
		return String.valueOf(RacerUtils.lapCount.apply(racer));
	}

	@Override
	public Comparator<Racer> getComparator() {
		return Comparator.comparing(RacerUtils.lapCount::apply);
	}

}
