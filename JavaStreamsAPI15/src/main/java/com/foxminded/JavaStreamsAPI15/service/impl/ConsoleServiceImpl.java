package com.foxminded.JavaStreamsAPI15.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.foxminded.JavaStreamsAPI15.model.Racer;
import com.foxminded.JavaStreamsAPI15.service.ConsoleService;
import com.foxminded.JavaStreamsAPI15.service.TableBuilderService;
import com.foxminded.JavaStreamsAPI15.table.column.impl.ColumnType;
import com.foxminded.JavaStreamsAPI15.table.impl.TableDescriptorFactory;
import com.foxminded.JavaStreamsAPI15.table.impl.TableType;

@Service
public class ConsoleServiceImpl implements ConsoleService {

	TableBuilderService tableBuilderService;

	private final Map<ColumnType, Integer> spacesForColumnType;

	public ConsoleServiceImpl(TableBuilderService tableBuilderService) {
		this.tableBuilderService = tableBuilderService;
		spacesForColumnType = new HashMap<>();
		spacesForColumnType.put(ColumnType.AvgTimeColumn, 9);
		spacesForColumnType.put(ColumnType.BestTimeColumn, 9);
		spacesForColumnType.put(ColumnType.LapCountColumn, 2);
		spacesForColumnType.put(ColumnType.NameColumn, 25);
		spacesForColumnType.put(ColumnType.TeamColumn, 35);
		spacesForColumnType.put(ColumnType.NoColumn, 2);
	}

	public String outputTable(TableType tableType, List<Racer> racers) {

		Map<ColumnType, List<String>> tableData = tableBuilderService.buildTable(tableType, racers);
		List<ColumnType> columnTypes = TableDescriptorFactory.getTable(tableType).getColumns();

		StringBuilder result = new StringBuilder();
		result.append(buildHeader(columnTypes, tableType));
		result.append(buildDataPart(tableData, columnTypes));
		result.append(buildFooter(columnTypes));

		return result.toString();
	}

	private StringBuilder buildDataPart(Map<ColumnType, List<String>> tableData, List<ColumnType> columnTypes) {
		StringBuilder data = new StringBuilder();

		if (!tableData.isEmpty() && !columnTypes.isEmpty()) {
			for (String No : tableData.get(ColumnType.NoColumn)) {
				data.append(String.format("|%-" + spacesForColumnType.get(ColumnType.NoColumn) + "s", No));
				for (ColumnType type : columnTypes) {
					data.append(String.format("|%-" + spacesForColumnType.get(type) + "s",
							tableData.get(type).get(tableData.get(ColumnType.NoColumn).indexOf(No))));
				}
				data.append("|\n");
			}
		}
		return data;
	}

	private StringBuilder buildHeader(List<ColumnType> columnTypes, TableType tableType) {
		StringBuilder header = new StringBuilder();
		String hyphens;
		Integer hyphensAmount = getHyphensAmount(columnTypes);

		hyphens = "-".repeat(hyphensAmount);
		header.append("+" + hyphens + "+\n");
		header.append(getTableNameStringInHeader(hyphensAmount, tableType));
		header.append("+" + hyphens + "+\n");

		return header;
	}

	private StringBuilder getTableNameStringInHeader(Integer hyphensAmount, TableType tableType) {
		StringBuilder tableNameString = new StringBuilder();
		int nameSymbolsFirstPartAmount = tableType.toString().length() / 2;
		int spacesBeforeNameAmount = hyphensAmount / 2 - nameSymbolsFirstPartAmount;
		int spacesAfterNameAmount = hyphensAmount - spacesBeforeNameAmount - tableType.toString().length();
		String spacesBeforeName = " ".repeat(spacesBeforeNameAmount);
		String spacesAfterName = " ".repeat(spacesAfterNameAmount);
		tableNameString.append("|" + spacesBeforeName + tableType.toString() + spacesAfterName + "|\n");

		return tableNameString;
	}

	private StringBuilder buildFooter(List<ColumnType> columnTypes) {
		StringBuilder footer = new StringBuilder();
		footer.append("+" + "-".repeat(getHyphensAmount(columnTypes)) + "+\n");
		return footer;
	}

	private Integer getHyphensAmount(List<ColumnType> columnTypes) {
		return columnTypes.stream().map(spacesForColumnType::get).reduce(0, Integer::sum) + columnTypes.size()
				+ spacesForColumnType.get(ColumnType.NoColumn);
	}

}
