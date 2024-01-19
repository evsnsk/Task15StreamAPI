package com.foxminded.JavaStreamsAPI15.table.impl;

import java.util.ArrayList;
import java.util.List;

import com.foxminded.JavaStreamsAPI15.table.TableDescriptor;
import com.foxminded.JavaStreamsAPI15.table.column.impl.ColumnType;

public class NamesTable implements TableDescriptor {
	private static final String TITLE = "Racers names";
	private List<ColumnType> columns;
	private ColumnType defaultSortColumnType;

	public NamesTable() {
		columns = new ArrayList<>();
		defaultSortColumnType = ColumnType.NameColumn;
		columns.add(ColumnType.NameColumn);
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
