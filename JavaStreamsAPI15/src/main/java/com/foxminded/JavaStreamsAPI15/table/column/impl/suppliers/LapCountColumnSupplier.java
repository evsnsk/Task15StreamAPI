package com.foxminded.JavaStreamsAPI15.table.column.impl.suppliers;

import com.foxminded.JavaStreamsAPI15.table.column.Column;
import com.foxminded.JavaStreamsAPI15.table.column.ColumnSupplier;
import com.foxminded.JavaStreamsAPI15.table.column.impl.ColumnType;
import com.foxminded.JavaStreamsAPI15.table.column.impl.LapCountColumn;

public class LapCountColumnSupplier implements ColumnSupplier {

	@Override
	public Column get() {
		return new LapCountColumn();
	}

	@Override
	public ColumnType getType() {
		return ColumnType.LapCountColumn;
	}

}
