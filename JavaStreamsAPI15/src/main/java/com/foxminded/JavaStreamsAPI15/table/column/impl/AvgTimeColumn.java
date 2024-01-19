package com.foxminded.JavaStreamsAPI15.table.column.impl;

import java.util.Comparator;

import com.foxminded.JavaStreamsAPI15.model.Racer;
import com.foxminded.JavaStreamsAPI15.table.column.Column;
import com.foxminded.JavaStreamsAPI15.utils.RacerUtils;

public class AvgTimeColumn implements Column {

	private final String TITLE = "Avg Time";

	@Override
	public String getTitle() {
		return TITLE;
	}

	@Override
	public String getData(Racer racer) {
		if (racer != null) {
			return RacerUtils.formatDuration(RacerUtils.avgLapTime.apply(racer));
		} else {
			return "";
		}
	}

	@Override
	public Comparator<Racer> getComparator() {
		return Comparator.comparing(RacerUtils.avgLapTime::apply).reversed();
	}

}
