package com.foxminded.JavaStreamsAPI15.table.column;

import java.util.Comparator;

import com.foxminded.JavaStreamsAPI15.model.Racer;

public interface Column {

	String getTitle();

	String getData(Racer racer);

	Comparator<Racer> getComparator();

}
