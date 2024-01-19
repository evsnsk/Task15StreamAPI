package com.foxminded.JavaStreamsAPI15.table.column.impl.suppliers;

import com.foxminded.JavaStreamsAPI15.table.column.Column;
import com.foxminded.JavaStreamsAPI15.table.column.ColumnSupplier;
import com.foxminded.JavaStreamsAPI15.table.column.impl.ColumnType;
import com.foxminded.JavaStreamsAPI15.table.column.impl.TeamColumn;

public class TeamColumnSupplier implements ColumnSupplier {

	@Override
	public Column get() {
		return new TeamColumn();
	}

	@Override
	public ColumnType getType() {
		return ColumnType.TeamColumn;
	}

}
