package com.foxminded.JavaStreamsAPI15.service;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.foxminded.JavaStreamsAPI15.model.Lap;
import com.foxminded.JavaStreamsAPI15.model.Racer;
import com.foxminded.JavaStreamsAPI15.model.entity.LapEntity;
import com.foxminded.JavaStreamsAPI15.model.entity.RacerEntity;
import com.foxminded.JavaStreamsAPI15.repository.LapEntityRepository;
import com.foxminded.JavaStreamsAPI15.repository.RacerEntityRepository;
import com.foxminded.JavaStreamsAPI15.service.impl.RacerServiceImpl;

@SpringBootTest(classes = { RacerServiceImpl.class })
class RacerServiceImplTest {

	@MockBean
	RacerEntityRepository racerEntityRepository;
	@MockBean
	LapEntityRepository lapEntityRepository;
	@Autowired
	RacerServiceImpl racerServiceImpl;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

	public void init() {

	}

	@Test
	void test_findAll() {
		List<LapEntity> lapEntitiyStarts = new ArrayList<>();
		lapEntitiyStarts.add(new LapEntity("VBM", LocalDateTime.parse("2019-07-12_12:23:42.474", formatter)));
		lapEntitiyStarts.add(new LapEntity("CSM", LocalDateTime.parse("2019-07-12_12:27:30.724", formatter)));
		List<LapEntity> lapEntitiyFinishes = new ArrayList<>();
		lapEntitiyFinishes.add(new LapEntity("VBM", LocalDateTime.parse("2019-07-12_12:25:08.224", formatter)));
		lapEntitiyFinishes.add(new LapEntity("CSM", LocalDateTime.parse("2019-07-12_12:28:56.396", formatter)));
		List<RacerEntity> racerEntities = new ArrayList<>();
		racerEntities.add(new RacerEntity("CSM", "Carlos Sainz", "MCLAREN RENAULT"));
		racerEntities.add(new RacerEntity("VBM", "Valtteri Bottas", "MERCEDES"));
		List<Lap> carlosLap = new ArrayList<>();
		carlosLap.add(new Lap(LocalDateTime.parse("2019-07-12_12:27:30.724", formatter),
				LocalDateTime.parse("2019-07-12_12:28:56.396", formatter)));
		List<Lap> valtteriLap = new ArrayList<>();
		valtteriLap.add(new Lap(LocalDateTime.parse("2019-07-12_12:23:42.474", formatter),
				LocalDateTime.parse("2019-07-12_12:25:08.224", formatter)));

		List<Racer> expectedRacers = new ArrayList<>();
		expectedRacers.add(new Racer("Carlos Sainz", "MCLAREN RENAULT", carlosLap));
		expectedRacers.add(new Racer("Valtteri Bottas", "MERCEDES", valtteriLap));

		when(lapEntityRepository.findAllStarts()).thenReturn(lapEntitiyStarts);
		when(lapEntityRepository.findAllFinishes()).thenReturn(lapEntitiyFinishes);
		when(racerEntityRepository.findAll()).thenReturn(racerEntities);

		Assertions.assertThat(racerServiceImpl.findAll()).isNotEmpty();
		Assertions.assertThat(racerServiceImpl.findAll()).usingRecursiveComparison().isEqualTo(expectedRacers);

	}

	@Test
	void testParseLaps() {
		List<Lap> expectedLapList = new ArrayList<>();
		expectedLapList.add(new Lap(LocalDateTime.parse("2019-07-12_12:23:42.474", formatter),
				LocalDateTime.parse("2019-07-12_12:25:08.224", formatter)));
		expectedLapList.add(new Lap(LocalDateTime.parse("2019-07-12_12:27:30.724", formatter),
				LocalDateTime.parse("2019-07-12_12:28:56.396", formatter)));

		List<LapEntity> lapEntities = new ArrayList<>();
		lapEntities.add(new LapEntity("VBM", LocalDateTime.parse("2019-07-12_12:23:42.474", formatter)));
		lapEntities.add(new LapEntity("VBM", LocalDateTime.parse("2019-07-12_12:25:08.224", formatter)));
		lapEntities.add(new LapEntity("VBM", LocalDateTime.parse("2019-07-12_12:27:30.724", formatter)));
		lapEntities.add(new LapEntity("VBM", LocalDateTime.parse("2019-07-12_12:28:56.396", formatter)));

		Assertions.assertThat(racerServiceImpl.parseLaps(lapEntities)).isNotEmpty();
		Assertions.assertThat(racerServiceImpl.parseLaps(lapEntities)).usingRecursiveComparison()
				.isEqualTo(expectedLapList);
	}

	@Test
	void testParseLaps_LastLapIsNotFinished() {
		List<Lap> expectedLapList = new ArrayList<>();
		expectedLapList.add(new Lap(LocalDateTime.parse("2019-07-12_12:23:42.474", formatter),
				LocalDateTime.parse("2019-07-12_12:25:08.224", formatter)));
		expectedLapList.add(new Lap(LocalDateTime.parse("2019-07-12_12:27:30.724", formatter), LocalDateTime.MIN));

		List<LapEntity> lapEntities = new ArrayList<>();
		lapEntities.add(new LapEntity("VBM", LocalDateTime.parse("2019-07-12_12:23:42.474", formatter)));
		lapEntities.add(new LapEntity("VBM", LocalDateTime.parse("2019-07-12_12:25:08.224", formatter)));
		lapEntities.add(new LapEntity("VBM", LocalDateTime.parse("2019-07-12_12:27:30.724", formatter)));

		Assertions.assertThat(racerServiceImpl.parseLaps(lapEntities)).isNotEmpty();
		Assertions.assertThat(racerServiceImpl.parseLaps(lapEntities)).usingRecursiveComparison()
				.isEqualTo(expectedLapList);
	}

	@Test
	void testParseLaps_LapEntitiesIsEmpty() {
		List<LapEntity> lapEntities = new ArrayList<>();
		Assertions.assertThat(racerServiceImpl.parseLaps(lapEntities)).isEmpty();
	}

}
