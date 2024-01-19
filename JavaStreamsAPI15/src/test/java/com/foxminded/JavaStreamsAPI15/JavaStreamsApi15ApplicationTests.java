package com.foxminded.JavaStreamsAPI15;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import com.foxminded.JavaStreamsAPI15.service.MenuServise;
import com.foxminded.JavaStreamsAPI15.service.UserInputOutput;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("disableMenu")
class JavaStreamsApi15ApplicationTests {

	@Autowired
	MenuServise menuServise;

	@MockBean
	UserInputOutput userInputOutput;

	@Captor
	private ArgumentCaptor<String> stringArgumentCaptor;

	@Test
	@Timeout(10)
	void contextLoads() {
		when(userInputOutput.read()).thenReturn("Racers best lap", "Racers names", "Racers avg lap time",
				"Racers lap count", "exit");

		menuServise.menuStart();
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
				|1 |Valtteri Bottas          |MERCEDES                           |01:25.093|
				|2 |Lewis Hamilton           |MERCEDES                           |01:25.099|
				|3 |Charles Leclerc          |FERRARI                            |01:25.172|
				|4 |Max Verstappen           |RED BULL RACING HONDA              |01:25.276|
				|5 |Sebastian Vettel         |FERRARI                            |01:25.787|
				|6 |Pierre Gasly             |RED BULL RACING HONDA              |01:26.038|
				|7 |Lando Norris             |MCLAREN RENAULT                    |01:26.079|
				|8 |Daniel Ricciardo         |RENAULT                            |01:26.182|
				|9 |Carlos Sainz             |MCLAREN RENAULT                    |01:26.203|
				|10|Alexander Albon          |SCUDERIA TORO ROSSO HONDA          |01:26.345|
				|11|Romain Grosjean          |HAAS FERRARI                       |01:26.347|
				|12|Nico Hulkenberg          |RENAULT                            |01:26.386|
				|13|Antonio Giovinazzi       |ALFA ROMEO RACING FERRARI          |01:26.449|
				|14|Kimi Räikkönen           |ALFA ROMEO RACING FERRARI          |01:26.546|
				|15|Sergio Perez             |RACING POINT BWT MERCEDES          |01:26.649|
				|16|Kevin Magnussen          |HAAS FERRARI                       |01:26.662|
				|17|Daniil Kvyat             |SCUDERIA TORO ROSSO HONDA          |01:26.721|
				|18|Lance Stroll             |RACING POINT BWT MERCEDES          |01:26.762|
				|19|George Russell           |WILLIAMS MERCEDES                  |01:27.789|
				|20|Robert Kubica            |WILLIAMS MERCEDES                  |--:--.---|
				+--------------------------------------------------------------------------+

				Racers best lap|
				Racers names|
				Racers avg lap time|
				Racers lap count|



				Table type: Racers names

				+----------------------------+
				|         NamesTable         |
				+----------------------------+
				|1 |Alexander Albon          |
				|2 |Antonio Giovinazzi       |
				|3 |Carlos Sainz             |
				|4 |Charles Leclerc          |
				|5 |Daniel Ricciardo         |
				|6 |Daniil Kvyat             |
				|7 |George Russell           |
				|8 |Kevin Magnussen          |
				|9 |Kimi Räikkönen           |
				|10|Lance Stroll             |
				|11|Lando Norris             |
				|12|Lewis Hamilton           |
				|13|Max Verstappen           |
				|14|Nico Hulkenberg          |
				|15|Pierre Gasly             |
				|16|Robert Kubica            |
				|17|Romain Grosjean          |
				|18|Sebastian Vettel         |
				|19|Sergio Perez             |
				|20|Valtteri Bottas          |
				+----------------------------+

				Racers best lap|
				Racers names|
				Racers avg lap time|
				Racers lap count|



				Table type: Racers avg lap time

				+--------------------------------------------------------------------------+
				|                              AvgLapTimeTable                             |
				+--------------------------------------------------------------------------+
				|1 |Charles Leclerc          |FERRARI                            |01:25.417|
				|2 |Lewis Hamilton           |MERCEDES                           |01:25.484|
				|3 |Valtteri Bottas          |MERCEDES                           |01:25.505|
				|4 |Max Verstappen           |RED BULL RACING HONDA              |01:25.608|
				|5 |Sebastian Vettel         |FERRARI                            |01:25.902|
				|6 |Pierre Gasly             |RED BULL RACING HONDA              |01:26.155|
				|7 |Lando Norris             |MCLAREN RENAULT                    |01:26.229|
				|8 |Daniel Ricciardo         |RENAULT                            |01:26.297|
				|9 |Carlos Sainz             |MCLAREN RENAULT                    |01:26.390|
				|10|Alexander Albon          |SCUDERIA TORO ROSSO HONDA          |01:26.410|
				|11|Nico Hulkenberg          |RENAULT                            |01:26.450|
				|12|Antonio Giovinazzi       |ALFA ROMEO RACING FERRARI          |01:26.484|
				|13|Kimi Räikkönen           |ALFA ROMEO RACING FERRARI          |01:26.552|
				|14|Romain Grosjean          |HAAS FERRARI                       |01:26.552|
				|15|Kevin Magnussen          |HAAS FERRARI                       |01:26.662|
				|16|Daniil Kvyat             |SCUDERIA TORO ROSSO HONDA          |01:26.721|
				|17|Lance Stroll             |RACING POINT BWT MERCEDES          |01:26.762|
				|18|Sergio Perez             |RACING POINT BWT MERCEDES          |01:26.788|
				|19|George Russell           |WILLIAMS MERCEDES                  |01:27.789|
				|20|Robert Kubica            |WILLIAMS MERCEDES                  |--:--.---|
				+--------------------------------------------------------------------------+

				Racers best lap|
				Racers names|
				Racers avg lap time|
				Racers lap count|



				Table type: Racers lap count

				+-------------------------------------------------------------------+
				|                           LapCountTable                           |
				+-------------------------------------------------------------------+
				|1 |Lewis Hamilton           |MERCEDES                           |3 |
				|2 |Alexander Albon          |SCUDERIA TORO ROSSO HONDA          |3 |
				|3 |Nico Hulkenberg          |RENAULT                            |3 |
				|4 |Max Verstappen           |RED BULL RACING HONDA              |3 |
				|5 |Lando Norris             |MCLAREN RENAULT                    |3 |
				|6 |Charles Leclerc          |FERRARI                            |3 |
				|7 |Valtteri Bottas          |MERCEDES                           |3 |
				|8 |Sebastian Vettel         |FERRARI                            |3 |
				|9 |Daniel Ricciardo         |RENAULT                            |3 |
				|10|Carlos Sainz             |MCLAREN RENAULT                    |2 |
				|11|Kimi Räikkönen           |ALFA ROMEO RACING FERRARI          |2 |
				|12|Sergio Perez             |RACING POINT BWT MERCEDES          |2 |
				|13|Antonio Giovinazzi       |ALFA ROMEO RACING FERRARI          |2 |
				|14|Pierre Gasly             |RED BULL RACING HONDA              |2 |
				|15|Romain Grosjean          |HAAS FERRARI                       |2 |
				|16|Kevin Magnussen          |HAAS FERRARI                       |1 |
				|17|Daniil Kvyat             |SCUDERIA TORO ROSSO HONDA          |1 |
				|18|George Russell           |WILLIAMS MERCEDES                  |1 |
				|19|Lance Stroll             |RACING POINT BWT MERCEDES          |1 |
				|20|Robert Kubica            |WILLIAMS MERCEDES                  |0 |
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
