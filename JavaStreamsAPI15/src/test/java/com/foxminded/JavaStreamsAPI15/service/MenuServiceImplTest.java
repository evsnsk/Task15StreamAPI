package com.foxminded.JavaStreamsAPI15.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.foxminded.JavaStreamsAPI15.model.Lap;
import com.foxminded.JavaStreamsAPI15.model.Racer;
import com.foxminded.JavaStreamsAPI15.service.impl.MenuServiceImpl;
import com.foxminded.JavaStreamsAPI15.table.impl.TableType;

@SpringBootTest(classes = { MenuServiceImpl.class })
class MenuServiceImplTest {

	@MockBean
	ConsoleService consoleService;
	@MockBean
	RacerService racerService;
	@MockBean
	UserInputOutput userInputOutput;

	@Autowired
	MenuServiceImpl menuService;

	@Captor
	private ArgumentCaptor<String> stringArgumentCaptor;

	@Test
	void test_showTableSelectMenu() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
		List<Lap> carlosLap = new ArrayList<>();
		carlosLap.add(new Lap(LocalDateTime.parse("2019-07-12_12:27:00.000", formatter),
				LocalDateTime.parse("2019-07-12_12:27:56.396", formatter)));
		List<Lap> valtteriLap = new ArrayList<>();
		valtteriLap.add(new Lap(LocalDateTime.parse("2019-07-12_12:23:42.474", formatter),
				LocalDateTime.parse("2019-07-12_12:25:08.224", formatter)));

		List<Racer> racers = new ArrayList<>();
		racers.add(new Racer("Carlos Sainz2", "MCLAREN RENAULT", carlosLap));
		racers.add(new Racer("Valtteri Bottas", "MERCEDES", valtteriLap));

		String avgLapTimeTable = """
				+--------------------------------------------------------------------------+
				|                              AvgLapTimeTable                             |
				+--------------------------------------------------------------------------+
				|1 |Carlos Sainz             |MCLAREN RENAULT                    |00:56.396|
				|2 |Valtteri Bottas          |MERCEDES                           |01:25.750|
				+--------------------------------------------------------------------------+
				""";
		String namesTable = """
				+----------------------------+
				|         NamesTable         |
				+----------------------------+
				|1 |Carlos Sainz             |
				|2 |Valtteri Bottas          |
				+----------------------------+
				""";
		String bestLapTable = """
				+--------------------------------------------------------------------------+
				|                               BestLapTable                               |
				+--------------------------------------------------------------------------+
				|1 |Carlos Sainz             |MCLAREN RENAULT                    |00:56.396|
				|2 |Valtteri Bottas          |MERCEDES                           |01:25.750|
				+--------------------------------------------------------------------------+
				""";
		String lapCountTable = """
				+-------------------------------------------------------------------+
				|                           LapCountTable                           |
				+-------------------------------------------------------------------+
				|1 |Carlos Sainz             |MCLAREN RENAULT                    |1 |
				|2 |Valtteri Bottas          |MERCEDES                           |1 |
				+-------------------------------------------------------------------+
				""";

		when(racerService.findAll()).thenReturn(racers);

		when(consoleService.outputTable(TableType.AvgLapTimeTable, racers)).thenReturn(avgLapTimeTable);
		when(consoleService.outputTable(TableType.NamesTable, racers)).thenReturn(namesTable);
		when(consoleService.outputTable(TableType.BestLapTable, racers)).thenReturn(bestLapTable);
		when(consoleService.outputTable(TableType.LapCountTable, racers)).thenReturn(lapCountTable);

		when(userInputOutput.read()).thenReturn("Racers best lap", "Racers names", "Racers avg lap time",
				"Racers lap count", "exit");

		menuService.menuStart();
		verify(racerService, times(1)).findAll();
		verify(userInputOutput, atLeast(1)).print(stringArgumentCaptor.capture());
		verify(userInputOutput, times(5)).read();

		String expected = """
				Racers best lap|
				Racers names|
				Racers avg lap time|
				Racers lap count|



				Table type: Racers best lap

				+--------------------------------------------------------------------------+
				|                               BestLapTable                               |
				+--------------------------------------------------------------------------+
				|1 |Carlos Sainz             |MCLAREN RENAULT                    |00:56.396|
				|2 |Valtteri Bottas          |MERCEDES                           |01:25.750|
				+--------------------------------------------------------------------------+

				Racers best lap|
				Racers names|
				Racers avg lap time|
				Racers lap count|



				Table type: Racers names

				+----------------------------+
				|         NamesTable         |
				+----------------------------+
				|1 |Carlos Sainz             |
				|2 |Valtteri Bottas          |
				+----------------------------+

				Racers best lap|
				Racers names|
				Racers avg lap time|
				Racers lap count|



				Table type: Racers avg lap time

				+--------------------------------------------------------------------------+
				|                              AvgLapTimeTable                             |
				+--------------------------------------------------------------------------+
				|1 |Carlos Sainz             |MCLAREN RENAULT                    |00:56.396|
				|2 |Valtteri Bottas          |MERCEDES                           |01:25.750|
				+--------------------------------------------------------------------------+

				Racers best lap|
				Racers names|
				Racers avg lap time|
				Racers lap count|



				Table type: Racers lap count

				+-------------------------------------------------------------------+
				|                           LapCountTable                           |
				+-------------------------------------------------------------------+
				|1 |Carlos Sainz             |MCLAREN RENAULT                    |1 |
				|2 |Valtteri Bottas          |MERCEDES                           |1 |
				+-------------------------------------------------------------------+

				Racers best lap|
				Racers names|
				Racers avg lap time|
				Racers lap count|

				""";
		String actual = stringArgumentCaptor.getAllValues().stream().collect(Collectors.joining("\n"));
		assertEquals(expected, actual);
	}
}
