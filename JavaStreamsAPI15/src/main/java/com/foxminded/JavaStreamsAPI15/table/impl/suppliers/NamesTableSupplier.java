package com.foxminded.JavaStreamsAPI15.table.impl.suppliers;

import com.foxminded.JavaStreamsAPI15.table.TableDescriptor;
import com.foxminded.JavaStreamsAPI15.table.TableDescriptorSupplier;
import com.foxminded.JavaStreamsAPI15.table.impl.NamesTable;
import com.foxminded.JavaStreamsAPI15.table.impl.TableType;

public class NamesTableSupplier implements TableDescriptorSupplier {

	@Override
	public TableDescriptor get() {
		return new NamesTable();
	}

	@Override
	public TableType getType() {
		return TableType.NamesTable;
	}

}
