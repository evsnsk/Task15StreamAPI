package com.foxminded.JavaStreamsAPI15.service;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.foxminded.JavaStreamsAPI15.model.Lap;
import com.foxminded.JavaStreamsAPI15.model.Racer;
import com.foxminded.JavaStreamsAPI15.service.impl.ConsoleServiceImpl;
import com.foxminded.JavaStreamsAPI15.table.column.impl.ColumnType;
import com.foxminded.JavaStreamsAPI15.table.impl.TableType;
import com.foxminded.JavaStreamsAPI15.utils.RacerUtils;

@SpringBootTest(classes = { ConsoleServiceImpl.class })
class ConsoleServiceImplTest {

	@MockBean(name = "tableBuilderService")
	TableBuilderService tableBuilderService;

	@Autowired
	ConsoleServiceImpl consoleServiceImpl;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
	List<Racer> racers;
	List<Lap> carlosLap;
	List<Lap> valtteriLap;
	TableType tableType;
	Map<ColumnType, List<String>> resultTable;

	@BeforeEach
	public void init() {
		racers = new ArrayList<>();
		carlosLap = new ArrayList<>();
		carlosLap.add(new Lap(LocalDateTime.parse("2019-07-12_12:27:00.000", formatter),
				LocalDateTime.parse("2019-07-12_12:27:56.396", formatter)));
		valtteriLap = new ArrayList<>();
		valtteriLap.add(new Lap(LocalDateTime.parse("2019-07-12_12:23:42.474", formatter),
				LocalDateTime.parse("2019-07-12_12:25:08.224", formatter)));
		racers.add(new Racer("Carlos Sainz2", "MCLAREN RENAULT", carlosLap));
		racers.add(new Racer("Valtteri Bottas", "MERCEDES", valtteriLap));

		resultTable = new HashMap<>();
		List<String> NoColumn = new ArrayList<>();
		NoColumn.add("1");
		NoColumn.add("2");

	}

	@Test
	void testOutputTable_AvgLapTime() {
		resultTable.put(ColumnType.NoColumn, new ArrayList<String>(Arrays.asList("1", "2")));
		resultTable.put(ColumnType.NameColumn, new ArrayList<String>(Arrays.asList("Carlos Sainz", "Valtteri Bottas")));
		resultTable.put(ColumnType.TeamColumn, new ArrayList<String>(Arrays.asList("MCLAREN RENAULT", "MERCEDES")));
		resultTable.put(ColumnType.AvgTimeColumn,
				new ArrayList<String>(Arrays.asList(
						RacerUtils.formatDuration(
								RacerUtils.avgLapTime.apply(new Racer("Carlos Sainz", "MCLAREN RENAULT", carlosLap))),
						RacerUtils.formatDuration(
								RacerUtils.avgLapTime.apply(new Racer("Valtteri Bottas", "MERCEDES", valtteriLap))))));

		tableType = TableType.AvgLapTimeTable;
		String expectedResult = """
				+--------------------------------------------------------------------------+
				|                              AvgLapTimeTable                             |
				+--------------------------------------------------------------------------+
				|1 |Carlos Sainz             |MCLAREN RENAULT                    |00:56.396|
				|2 |Valtteri Bottas          |MERCEDES                           |01:25.750|
				+--------------------------------------------------------------------------+
				""";

		when(tableBuilderService.buildTable(tableType, racers)).thenReturn(resultTable);

		Assertions.assertThat(consoleServiceImpl.outputTable(tableType, racers)).isNotEmpty();
		Assertions.assertThat(consoleServiceImpl.outputTable(tableType, racers)).isEqualTo(expectedResult);
	}

	@Test
	void testOutputTable_NamesTable() {
		resultTable.put(ColumnType.NoColumn, new ArrayList<String>(Arrays.asList("1", "2")));
		resultTable.put(ColumnType.NameColumn, new ArrayList<String>(Arrays.asList("Carlos Sainz", "Valtteri Bottas")));

		tableType = TableType.NamesTable;
		String expectedResult = """
				+----------------------------+
				|         NamesTable         |
				+----------------------------+
				|1 |Carlos Sainz             |
				|2 |Valtteri Bottas          |
				+----------------------------+
				""";

		when(tableBuilderService.buildTable(tableType, racers)).thenReturn(resultTable);

		Assertions.assertThat(consoleServiceImpl.outputTable(tableType, racers)).isNotEmpty();
		Assertions.assertThat(consoleServiceImpl.outputTable(tableType, racers)).isEqualTo(expectedResult);
	}

	@Test
	void testOutputTable_BestLapTable() {
		resultTable.put(ColumnType.NoColumn, new ArrayList<String>(Arrays.asList("1", "2")));
		resultTable.put(ColumnType.NameColumn, new ArrayList<String>(Arrays.asList("Carlos Sainz", "Valtteri Bottas")));
		resultTable.put(ColumnType.TeamColumn, new ArrayList<String>(Arrays.asList("MCLAREN RENAULT", "MERCEDES")));
		resultTable.put(ColumnType.BestTimeColumn,
				new ArrayList<String>(Arrays.asList(
						RacerUtils.formatDuration(
								RacerUtils.bestLapTime.apply(new Racer("Carlos Sainz", "MCLAREN RENAULT", carlosLap))),
						RacerUtils.formatDuration(
								RacerUtils.bestLapTime.apply(new Racer("Valtteri Bottas", "MERCEDES", valtteriLap))))));

		tableType = TableType.BestLapTable;
		String expectedResult = """
				+--------------------------------------------------------------------------+
				|                               BestLapTable                               |
				+--------------------------------------------------------------------------+
				|1 |Carlos Sainz             |MCLAREN RENAULT                    |00:56.396|
				|2 |Valtteri Bottas          |MERCEDES                           |01:25.750|
				+--------------------------------------------------------------------------+
				""";

		when(tableBuilderService.buildTable(tableType, racers)).thenReturn(resultTable);

		Assertions.assertThat(consoleServiceImpl.outputTable(tableType, racers)).isNotEmpty();
		Assertions.assertThat(consoleServiceImpl.outputTable(tableType, racers)).isEqualTo(expectedResult);
	}

	@Test
	void testOutputTable_LapCountTable() {
		resultTable.put(ColumnType.NoColumn, new ArrayList<String>(Arrays.asList("1", "2")));
		resultTable.put(ColumnType.NameColumn, new ArrayList<String>(Arrays.asList("Carlos Sainz", "Valtteri Bottas")));
		resultTable.put(ColumnType.TeamColumn, new ArrayList<String>(Arrays.asList("MCLAREN RENAULT", "MERCEDES")));
		resultTable.put(ColumnType.LapCountColumn,
				new ArrayList<String>(Arrays.asList(
						RacerUtils.lapCount.apply(new Racer("Carlos Sainz", "MCLAREN RENAULT", carlosLap)).toString(),
						RacerUtils.lapCount.apply(new Racer("Valtteri Bottas", "MERCEDES", valtteriLap)).toString())));

		tableType = TableType.LapCountTable;
		String expectedResult = """
				+-------------------------------------------------------------------+
				|                           LapCountTable                           |
				+-------------------------------------------------------------------+
				|1 |Carlos Sainz             |MCLAREN RENAULT                    |1 |
				|2 |Valtteri Bottas          |MERCEDES                           |1 |
				+-------------------------------------------------------------------+
				""";

		when(tableBuilderService.buildTable(tableType, racers)).thenReturn(resultTable);

		Assertions.assertThat(consoleServiceImpl.outputTable(tableType, racers)).isNotEmpty();
		Assertions.assertThat(consoleServiceImpl.outputTable(tableType, racers)).isEqualTo(expectedResult);
	}

}
