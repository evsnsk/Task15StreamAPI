package com.foxminded.JavaStreamsAPI15.table.impl.suppliers;

import com.foxminded.JavaStreamsAPI15.table.TableDescriptor;
import com.foxminded.JavaStreamsAPI15.table.TableDescriptorSupplier;
import com.foxminded.JavaStreamsAPI15.table.impl.AvgLapTimeTable;
import com.foxminded.JavaStreamsAPI15.table.impl.TableType;

public class AvgLapTimeTableSupplier implements TableDescriptorSupplier {

	@Override
	public TableDescriptor get() {
		return new AvgLapTimeTable();
	}

	@Override
	public TableType getType() {
		return TableType.AvgLapTimeTable;
	}

}
