package com.foxminded.JavaStreamsAPI15.table.column.impl.suppliers;

import com.foxminded.JavaStreamsAPI15.table.column.Column;
import com.foxminded.JavaStreamsAPI15.table.column.ColumnSupplier;
import com.foxminded.JavaStreamsAPI15.table.column.impl.AvgTimeColumn;
import com.foxminded.JavaStreamsAPI15.table.column.impl.ColumnType;

public class AvgTimeColumnSupplier implements ColumnSupplier {

	@Override
	public Column get() {
		return new AvgTimeColumn();
	}

	@Override
	public ColumnType getType() {
		return ColumnType.AvgTimeColumn;
	}

}
