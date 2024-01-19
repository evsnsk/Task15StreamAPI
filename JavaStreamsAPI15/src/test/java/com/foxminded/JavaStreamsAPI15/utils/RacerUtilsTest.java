package com.foxminded.JavaStreamsAPI15.utils;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.foxminded.JavaStreamsAPI15.model.Lap;
import com.foxminded.JavaStreamsAPI15.model.Racer;

class RacerUtilsTest {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
	Lap lap;
	Lap notFinishedLap;

	List<Lap> lapsList = new ArrayList<>();
	Racer racer;

	@BeforeEach
	public void init() {
		lap = new Lap(LocalDateTime.parse("2019-07-12_12:23:42.474", formatter),
				LocalDateTime.parse("2019-07-12_12:25:08.224", formatter));
		notFinishedLap = new Lap(LocalDateTime.parse("2019-07-12_12:27:30.724", formatter), LocalDateTime.MIN);
		lapsList.add(lap);

	}

	@Test
	void testLapDuration() {
		Assertions.assertThat(RacerUtils.lapDuration.apply(lap)).isNotNull();
		Assertions.assertThat(RacerUtils.lapDuration.apply(lap)).isEqualTo(Duration.parse("PT1M25.75S"));
	}

	@Test
	void testLapDuration_LapIsNotFinished() {
		Assertions.assertThat(RacerUtils.lapDuration.apply(notFinishedLap)).isNotNull();
		Assertions.assertThat(RacerUtils.lapDuration.apply(notFinishedLap)).isEqualTo(ChronoUnit.FOREVER.getDuration());
	}

	@Test
	void testBestLap() {
		lapsList.add(new Lap(LocalDateTime.parse("2019-07-12_12:27:30.724", formatter),
				LocalDateTime.parse("2019-07-12_12:28:56.396", formatter)));
		racer = new Racer("Carlos Sainz", "MCLAREN RENAULT", lapsList);

		Assertions.assertThat(RacerUtils.bestLap.apply(racer)).isNotNull();
		Assertions.assertThat(RacerUtils.bestLap.apply(racer)).isEqualTo(lapsList.get(1));
	}

	@Test
	void testBestLap_WithNotFinishedLap() {
		lapsList.add(notFinishedLap);
		racer = new Racer("Carlos Sainz", "MCLAREN RENAULT", lapsList);

		Assertions.assertThat(RacerUtils.bestLap.apply(racer)).isNotNull();
		Assertions.assertThat(RacerUtils.bestLap.apply(racer)).isEqualTo(lapsList.get(0));
	}

	@Test
	void testBestLap_NotFinishedLapOnly() {
		List<Lap> laps = new ArrayList<>();
		laps.add(notFinishedLap);
		racer = new Racer("Carlos Sainz", "MCLAREN RENAULT", laps);

		Assertions.assertThat(RacerUtils.bestLap.apply(racer)).isNotNull();
		Assertions.assertThat(RacerUtils.bestLap.apply(racer)).isEqualTo(laps.get(0));
	}

	@Test
	void testBestLapDuration() {
		lapsList.add(new Lap(LocalDateTime.parse("2019-07-12_12:27:30.724", formatter),
				LocalDateTime.parse("2019-07-12_12:28:56.396", formatter)));
		racer = new Racer("Carlos Sainz", "MCLAREN RENAULT", lapsList);

		Assertions.assertThat(RacerUtils.bestLapTime.apply(racer)).isNotNull();
		Assertions.assertThat(RacerUtils.bestLapTime.apply(racer)).isEqualTo(Duration.parse("PT1M25.672S"));

	}

	@Test
	void testBestLapDuration_WithNotFinishedLap() {
		lapsList.add(notFinishedLap);
		racer = new Racer("Carlos Sainz", "MCLAREN RENAULT", lapsList);

		Assertions.assertThat(RacerUtils.bestLapTime.apply(racer)).isNotNull();
		Assertions.assertThat(RacerUtils.bestLapTime.apply(racer)).isEqualTo(Duration.parse("PT1M25.75S"));

	}

	@Test
	void testBestLapDuration_NotFinishedLapOnly() {
		List<Lap> laps = new ArrayList<>();
		laps.add(notFinishedLap);
		racer = new Racer("Carlos Sainz", "MCLAREN RENAULT", laps);

		Assertions.assertThat(RacerUtils.bestLapTime.apply(racer)).isNotNull();
		Assertions.assertThat(RacerUtils.bestLapTime.apply(racer)).isEqualTo(ChronoUnit.FOREVER.getDuration());
	}

	@Test
	void testAvgLapTime() {
		lapsList.add(new Lap(LocalDateTime.parse("2019-07-12_12:27:30.724", formatter),
				LocalDateTime.parse("2019-07-12_12:28:56.396", formatter)));
		racer = new Racer("Carlos Sainz", "MCLAREN RENAULT", lapsList);

		Assertions.assertThat(RacerUtils.avgLapTime.apply(racer)).isNotNull();
		Assertions.assertThat(RacerUtils.avgLapTime.apply(racer)).isEqualTo(Duration.parse("PT1M25.711S"));
	}

	@Test
	void testAvgLapTime_WithNotFinishedLap() {
		lapsList.add(notFinishedLap);
		racer = new Racer("Carlos Sainz", "MCLAREN RENAULT", lapsList);

		Assertions.assertThat(RacerUtils.avgLapTime.apply(racer)).isNotNull();
		Assertions.assertThat(RacerUtils.avgLapTime.apply(racer)).isEqualTo(Duration.parse("PT1M25.75S"));
	}

	@Test
	void testAvgLapTime_NotFinishedLapOnly() {
		List<Lap> laps = new ArrayList<>();
		laps.add(notFinishedLap);
		racer = new Racer("Carlos Sainz", "MCLAREN RENAULT", laps);

		Assertions.assertThat(RacerUtils.avgLapTime.apply(racer)).isNotNull();
		Assertions.assertThat(RacerUtils.avgLapTime.apply(racer)).isEqualTo(ChronoUnit.FOREVER.getDuration());

	}

	@Test
	void testFormatDuration() {
		Assertions.assertThat(RacerUtils.formatDuration(Duration.parse("PT1M25.75S"))).isNotNull();
		Assertions.assertThat(RacerUtils.formatDuration(Duration.parse("PT1M25.75S"))).isEqualTo("01:25.750");
	}

	@Test
	void testFormatDuration_NotFinishedLapOnly() {
		Assertions.assertThat(RacerUtils.formatDuration(ChronoUnit.FOREVER.getDuration())).isNotNull();
		Assertions.assertThat(RacerUtils.formatDuration(ChronoUnit.FOREVER.getDuration())).isEqualTo("--:--.---");
	}

	@Test
	void testLapCount() {
		lapsList.add(new Lap(LocalDateTime.parse("2019-07-12_12:27:30.724", formatter),
				LocalDateTime.parse("2019-07-12_12:28:56.396", formatter)));
		racer = new Racer("Carlos Sainz", "MCLAREN RENAULT", lapsList);

		Assertions.assertThat(RacerUtils.lapCount.apply(racer)).isNotNull();
		Assertions.assertThat(RacerUtils.lapCount.apply(racer)).isEqualTo(2);

	}

	@Test
	void testLapCount_WithNotFinishedLap() {
		lapsList.add(notFinishedLap);
		racer = new Racer("Carlos Sainz", "MCLAREN RENAULT", lapsList);

		Assertions.assertThat(RacerUtils.lapCount.apply(racer)).isNotNull();
		Assertions.assertThat(RacerUtils.lapCount.apply(racer)).isEqualTo(1);

	}

	@Test
	void testLapCount_NotFinishedLapOnly() {
		List<Lap> laps = new ArrayList<>();
		laps.add(notFinishedLap);
		racer = new Racer("Carlos Sainz", "MCLAREN RENAULT", laps);

		Assertions.assertThat(RacerUtils.lapCount.apply(racer)).isNotNull();
		Assertions.assertThat(RacerUtils.lapCount.apply(racer)).isEqualTo(0);

	}

}
