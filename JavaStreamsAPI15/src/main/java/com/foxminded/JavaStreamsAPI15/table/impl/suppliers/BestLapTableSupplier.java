package com.foxminded.JavaStreamsAPI15.table.impl.suppliers;

import com.foxminded.JavaStreamsAPI15.table.TableDescriptor;
import com.foxminded.JavaStreamsAPI15.table.TableDescriptorSupplier;
import com.foxminded.JavaStreamsAPI15.table.impl.BestLapTable;
import com.foxminded.JavaStreamsAPI15.table.impl.TableType;

public class BestLapTableSupplier implements TableDescriptorSupplier {

	@Override
	public TableDescriptor get() {
		return new BestLapTable();
	}

	@Override
	public TableType getType() {
		return TableType.BestLapTable;
	}

}
