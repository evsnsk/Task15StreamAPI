package com.foxminded.JavaStreamsAPI15.table.impl;

import java.util.HashMap;
import java.util.Map;

import com.foxminded.JavaStreamsAPI15.table.TableDescriptor;
import com.foxminded.JavaStreamsAPI15.table.TableDescriptorSupplier;
import com.foxminded.JavaStreamsAPI15.table.impl.suppliers.AvgLapTimeTableSupplier;
import com.foxminded.JavaStreamsAPI15.table.impl.suppliers.BestLapTableSupplier;
import com.foxminded.JavaStreamsAPI15.table.impl.suppliers.LapCountTableSupplier;
import com.foxminded.JavaStreamsAPI15.table.impl.suppliers.NamesTableSupplier;

public class TableDescriptorFactory {

	private static final Map<TableType, TableDescriptorSupplier> tableMap = new HashMap<>();

	static {
		tableMap.put(TableType.AvgLapTimeTable, new AvgLapTimeTableSupplier());
		tableMap.put(TableType.BestLapTable, new BestLapTableSupplier());
		tableMap.put(TableType.LapCountTable, new LapCountTableSupplier());
		tableMap.put(TableType.NamesTable, new NamesTableSupplier());
	}

	public static final TableDescriptor getTable(TableType type) {
		return tableMap.get(type).get();
	}

}
