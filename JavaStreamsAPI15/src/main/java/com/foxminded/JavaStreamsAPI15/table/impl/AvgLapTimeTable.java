package com.foxminded.JavaStreamsAPI15.table.impl;

import java.util.ArrayList;
import java.util.List;

import com.foxminded.JavaStreamsAPI15.table.TableDescriptor;
import com.foxminded.JavaStreamsAPI15.table.column.impl.ColumnType;

public class AvgLapTimeTable implements TableDescriptor {

	private static final String TITLE = "Racers lap count";
	private List<ColumnType> columns;
	private ColumnType defaultSortColumnType;

	// No, Name, Team, Avg Lap Time
	public AvgLapTimeTable() {
		columns = new ArrayList<>();
		defaultSortColumnType = ColumnType.AvgTimeColumn;
		columns.add(ColumnType.NameColumn);
		columns.add(ColumnType.TeamColumn);
		columns.add(ColumnType.AvgTimeColumn);
	}

	@Override
	public String getTitle() {
		return TITLE;
	}

	@Override
	public List<ColumnType> getColumns() {
		return columns;
	}

	@Override
	public ColumnType getDefaultSortColumnType() {
		return defaultSortColumnType;
	}

	public void setSortColumnType(ColumnType sortColumn) {
		defaultSortColumnType = sortColumn;
	}

}
