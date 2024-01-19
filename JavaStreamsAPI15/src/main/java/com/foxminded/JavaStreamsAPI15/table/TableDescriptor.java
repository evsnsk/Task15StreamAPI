package com.foxminded.JavaStreamsAPI15.table;

import java.util.List;
import com.foxminded.JavaStreamsAPI15.table.column.impl.ColumnType;

public interface TableDescriptor {

	String getTitle();

	List<ColumnType> getColumns();

	ColumnType getDefaultSortColumnType();

}
