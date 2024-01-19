package com.foxminded.JavaStreamsAPI15.table;

import java.util.function.Supplier;
import com.foxminded.JavaStreamsAPI15.table.impl.TableType;

public interface TableDescriptorSupplier extends Supplier<TableDescriptor> {
	TableType getType();
}
