package com.foxminded.JavaStreamsAPI15.table.column.impl.suppliers;

import com.foxminded.JavaStreamsAPI15.table.column.Column;
import com.foxminded.JavaStreamsAPI15.table.column.ColumnSupplier;
import com.foxminded.JavaStreamsAPI15.table.column.impl.ColumnType;
import com.foxminded.JavaStreamsAPI15.table.column.impl.NameColumn;

public class NameColumnSupplier implements ColumnSupplier {

	@Override
	public Column get() {
		return new NameColumn();
	}

	@Override
	public ColumnType getType() {
		return ColumnType.NameColumn;
	}

}
