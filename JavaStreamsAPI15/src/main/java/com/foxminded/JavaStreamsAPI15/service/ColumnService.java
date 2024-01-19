package com.foxminded.JavaStreamsAPI15.service;

import java.util.List;

import com.foxminded.JavaStreamsAPI15.table.column.Column;

public interface ColumnService {

	Column getColumn(String name);

	List<Column> getColumns(List<String> names);

}
