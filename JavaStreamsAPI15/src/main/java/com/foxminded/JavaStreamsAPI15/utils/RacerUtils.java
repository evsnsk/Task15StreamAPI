package com.foxminded.JavaStreamsAPI15.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.Function;

import com.foxminded.JavaStreamsAPI15.model.Lap;
import com.foxminded.JavaStreamsAPI15.model.Racer;

public class RacerUtils {

	private RacerUtils() {
	}

	public static final Function<Lap, Duration> lapDuration = lap -> {
		if (lap.getFinish().equals(LocalDateTime.MIN)) {
			return ChronoUnit.FOREVER.getDuration();
		} else {
			return Duration.between(lap.getStart(), lap.getFinish());
		}
	};

	public static final Function<Racer, Lap> bestLap = racer -> {
		return racer.getLaps().stream().min(Comparator.comparing(lapDuration)).orElseThrow(NoSuchElementException::new);
	};

	public static final Function<Racer, Duration> bestLapTime = racer -> {
		return lapDuration.apply(bestLap.apply(racer));
	};

	public static final Function<Racer, Integer> lapCount = racer -> Math
			.toIntExact(racer.getLaps().stream().filter(it -> !it.getFinish().equals(LocalDateTime.MIN)).count());

	public static final Function<Racer, Duration> avgLapTime = racer -> {

		return racer.getLaps().stream().map(lapDuration::apply)
				.filter(duration -> !duration.equals(ChronoUnit.FOREVER.getDuration())).reduce((acc, i) -> acc.plus(i))
				.map(duration -> duration.dividedBy(lapCount.apply(racer))).orElse(ChronoUnit.FOREVER.getDuration());
	};

	public static String formatDuration(Duration duration) {
		if (duration == ChronoUnit.FOREVER.getDuration()) {
			return "--:--.---";
		} else {
			return "%02d:%02d.%03d".formatted(duration.toMinutesPart(), duration.toSecondsPart(),
					duration.toMillisPart());
		}
	}

}
