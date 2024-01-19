package com.foxminded.JavaStreamsAPI15.table.impl;

import java.util.ArrayList;
import java.util.List;

import com.foxminded.JavaStreamsAPI15.table.TableDescriptor;
import com.foxminded.JavaStreamsAPI15.table.column.impl.ColumnType;

public class BestLapTable implements TableDescriptor {

	private static final String TITLE = "Racers best lap";
	private List<ColumnType> columns;
	private ColumnType defaultSortColumnType;

	// No, Name, Team, Best Lap Time
	public BestLapTable() {
		columns = new ArrayList<>();
		defaultSortColumnType = ColumnType.BestTimeColumn;
		columns.add(ColumnType.NameColumn);
		columns.add(ColumnType.TeamColumn);
		columns.add(ColumnType.BestTimeColumn);
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
