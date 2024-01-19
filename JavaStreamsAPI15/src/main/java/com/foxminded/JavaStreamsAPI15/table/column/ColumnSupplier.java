package com.foxminded.JavaStreamsAPI15.table.column;

import java.util.function.Supplier;
import com.foxminded.JavaStreamsAPI15.table.column.impl.ColumnType;

public interface ColumnSupplier extends Supplier<Column> {

	ColumnType getType();

}
