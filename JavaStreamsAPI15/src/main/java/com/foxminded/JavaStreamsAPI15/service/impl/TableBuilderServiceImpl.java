package com.foxminded.JavaStreamsAPI15.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.foxminded.JavaStreamsAPI15.model.Racer;
import com.foxminded.JavaStreamsAPI15.service.TableBuilderService;
import com.foxminded.JavaStreamsAPI15.table.TableDescriptor;
import com.foxminded.JavaStreamsAPI15.table.column.Column;
import com.foxminded.JavaStreamsAPI15.table.column.impl.ColumnFactory;
import com.foxminded.JavaStreamsAPI15.table.column.impl.ColumnType;
import com.foxminded.JavaStreamsAPI15.table.impl.TableDescriptorFactory;
import com.foxminded.JavaStreamsAPI15.table.impl.TableType;

@Service
public class TableBuilderServiceImpl implements TableBuilderService {

	TableType tableType;
	TableDescriptor table;
	List<String> NoColumn;
	List<Racer> sortedRacers;
	Map<ColumnType, List<String>> abstrctTable;

	public TableBuilderServiceImpl() {
		abstrctTable = new HashMap<>();
		sortedRacers = new ArrayList<>();

	}

	@Override
	public Map<ColumnType, List<String>> buildTable(TableType tableType, List<Racer> racers) {
		this.tableType = tableType;
		table = TableDescriptorFactory.getTable(tableType);
		setNoColumnNumbers(racers);
		sortedRacers = sortRacersByDefaultColumn(racers);
		abstrctTable = getTableData();
		return abstrctTable;
	}

	private Map<ColumnType, List<String>> getTableData() {
		Map<ColumnType, List<String>> resultTable = new HashMap<>();
		resultTable.put(ColumnType.NoColumn, NoColumn);
		table.getColumns().stream().forEach(column -> resultTable.put(column,
				sortedRacers.stream().map(ColumnFactory.getColumn(column)::getData).toList()));
		return resultTable;
	}

	private List<Racer> sortRacersByDefaultColumn(List<Racer> racers) {
		List<Racer> sortedRacers = new ArrayList<>(racers);
		ColumnType defaultSortColumnType = this.table.getDefaultSortColumnType();
		Column defaultSortColumn = ColumnFactory.getColumn(defaultSortColumnType);

		for (int i = 0; i < sortedRacers.size(); i++) {
			for (int j = i + 1; j < sortedRacers.size(); j++) {
				if (defaultSortColumn.getComparator().compare(sortedRacers.get(j), sortedRacers.get(i)) > 0) {
					Racer temp = sortedRacers.get(j);
					sortedRacers.set(j, sortedRacers.get(i));
					sortedRacers.set(i, temp);
				}
			}
		}
		return sortedRacers;
	}

	public List<String> setNoColumnNumbers(List<Racer> racers) {
		NoColumn = new ArrayList<>(racers.size());
		racers.stream().forEach(racer -> this.NoColumn.add(String.valueOf(racers.indexOf(racer) + 1)));
		return NoColumn;
	}

	public TableType getTableType() {
		return tableType;
	}

	public TableDescriptor getTable() {
		return table;
	}

	public List<Racer> getSortedRacers() {
		return sortedRacers;
	}

	public List<String> getNoColumn() {
		return NoColumn;
	}

}
