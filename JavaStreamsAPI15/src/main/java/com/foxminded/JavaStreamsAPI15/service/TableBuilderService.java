package com.foxminded.JavaStreamsAPI15.service;

import java.util.List;
import java.util.Map;

import com.foxminded.JavaStreamsAPI15.model.Racer;
import com.foxminded.JavaStreamsAPI15.table.column.impl.ColumnType;
import com.foxminded.JavaStreamsAPI15.table.impl.TableType;

public interface TableBuilderService {

	Map<ColumnType, List<String>> buildTable(TableType tableType, List<Racer> racers);

}
