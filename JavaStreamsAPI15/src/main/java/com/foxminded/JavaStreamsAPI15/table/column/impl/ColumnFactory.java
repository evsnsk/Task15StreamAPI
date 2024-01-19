package com.foxminded.JavaStreamsAPI15.table.column.impl;

import java.util.HashMap;
import java.util.Map;
import com.foxminded.JavaStreamsAPI15.table.column.Column;
import com.foxminded.JavaStreamsAPI15.table.column.ColumnSupplier;
import com.foxminded.JavaStreamsAPI15.table.column.impl.suppliers.*;

public class ColumnFactory {

	private ColumnFactory() {
	}

	private static final Map<ColumnType, ColumnSupplier> columnMap = new HashMap<>();

	static {
		columnMap.put(ColumnType.AvgTimeColumn, new AvgTimeColumnSupplier());
		columnMap.put(ColumnType.BestTimeColumn, new BestTimeColumnSupplier());
		columnMap.put(ColumnType.LapCountColumn, new LapCountColumnSupplier());
		columnMap.put(ColumnType.NameColumn, new NameColumnSupplier());
		columnMap.put(ColumnType.TeamColumn, new TeamColumnSupplier());
		columnMap.put(ColumnType.NoColumn, new TeamColumnSupplier());
	}

	public static final Column getColumn(ColumnType type) {
		return columnMap.get(type).get();
	}

}
