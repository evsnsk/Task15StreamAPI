package com.foxminded.JavaStreamsAPI15.table.column.impl.suppliers;

import com.foxminded.JavaStreamsAPI15.table.column.Column;
import com.foxminded.JavaStreamsAPI15.table.column.ColumnSupplier;
import com.foxminded.JavaStreamsAPI15.table.column.impl.BestTimeColumn;
import com.foxminded.JavaStreamsAPI15.table.column.impl.ColumnType;

public class BestTimeColumnSupplier implements ColumnSupplier {

	@Override
	public Column get() {
		return new BestTimeColumn();
	}

	@Override
	public ColumnType getType() {
		return ColumnType.BestTimeColumn;
	}

}
