package com.foxminded.JavaStreamsAPI15.service;

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

import com.foxminded.JavaStreamsAPI15.model.Lap;
import com.foxminded.JavaStreamsAPI15.model.Racer;
import com.foxminded.JavaStreamsAPI15.service.impl.TableBuilderServiceImpl;
import com.foxminded.JavaStreamsAPI15.table.column.impl.ColumnType;
import com.foxminded.JavaStreamsAPI15.table.impl.TableType;
import com.foxminded.JavaStreamsAPI15.utils.RacerUtils;

@SpringBootTest(classes = { TableBuilderServiceImpl.class })
class TableBuilderServiceImplTest {

	@Autowired
	TableBuilderServiceImpl tableBuilderServiceImpl;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
	List<Racer> racers;
	List<Lap> carlosLap;
	List<Lap> valtteriLap;

	@BeforeEach
	public void init() {
		racers = new ArrayList<>();
		carlosLap = new ArrayList<>();
		carlosLap.add(new Lap(LocalDateTime.parse("2019-07-12_12:27:30.724", formatter),
				LocalDateTime.parse("2019-07-12_12:28:56.396", formatter)));
		valtteriLap = new ArrayList<>();
		valtteriLap.add(new Lap(LocalDateTime.parse("2019-07-12_12:23:42.474", formatter),
				LocalDateTime.parse("2019-07-12_12:25:08.224", formatter)));
		racers.add(new Racer("Carlos Sainz", "MCLAREN RENAULT", carlosLap));
		racers.add(new Racer("Valtteri Bottas", "MERCEDES", valtteriLap));

	}

	@Test
	void testBuildAvgLapTimeTable() {
		Map<ColumnType, List<String>> abstrctTable = new HashMap<>();
		TableType tableType = TableType.AvgLapTimeTable;
		List<String> NoColumn = new ArrayList<>();
		NoColumn.add("1");
		NoColumn.add("2");

		abstrctTable.put(ColumnType.NoColumn, new ArrayList<String>(Arrays.asList("1", "2")));
		abstrctTable.put(ColumnType.NameColumn,
				new ArrayList<String>(Arrays.asList("Carlos Sainz", "Valtteri Bottas")));
		abstrctTable.put(ColumnType.TeamColumn, new ArrayList<String>(Arrays.asList("MCLAREN RENAULT", "MERCEDES")));
		abstrctTable.put(ColumnType.AvgTimeColumn,
				new ArrayList<String>(Arrays.asList(
						RacerUtils.formatDuration(
								RacerUtils.avgLapTime.apply(new Racer("Carlos Sainz", "MCLAREN RENAULT", carlosLap))),
						RacerUtils.formatDuration(
								RacerUtils.avgLapTime.apply(new Racer("Valtteri Bottas", "MERCEDES", valtteriLap))))));

		Assertions.assertThat(tableBuilderServiceImpl.buildTable(tableType, racers)).isNotEmpty();
		Assertions.assertThat(tableBuilderServiceImpl.buildTable(tableType, racers)).usingRecursiveComparison()
				.isEqualTo(abstrctTable);
	}

	@Test
	void testSetNoColumnNumbers() {
		List<String> NoColumn = new ArrayList<>();
		NoColumn.add("1");
		NoColumn.add("2");

		Assertions.assertThat(tableBuilderServiceImpl.setNoColumnNumbers(racers)).isNotEmpty();
		Assertions.assertThat(tableBuilderServiceImpl.setNoColumnNumbers(racers)).isEqualTo(NoColumn);

	}

}
