package com.foxminded.JavaStreamsAPI15.table.impl.suppliers;

import com.foxminded.JavaStreamsAPI15.table.TableDescriptor;
import com.foxminded.JavaStreamsAPI15.table.TableDescriptorSupplier;
import com.foxminded.JavaStreamsAPI15.table.impl.LapCountTable;
import com.foxminded.JavaStreamsAPI15.table.impl.TableType;

public class LapCountTableSupplier implements TableDescriptorSupplier {

	@Override
	public TableDescriptor get() {
		return new LapCountTable();
	}

	@Override
	public TableType getType() {
		return TableType.LapCountTable;
	}

}
