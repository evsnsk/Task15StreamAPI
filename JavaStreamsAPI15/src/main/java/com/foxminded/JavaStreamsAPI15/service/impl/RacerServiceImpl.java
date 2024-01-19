package com.foxminded.JavaStreamsAPI15.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.foxminded.JavaStreamsAPI15.model.Lap;
import com.foxminded.JavaStreamsAPI15.model.Racer;
import com.foxminded.JavaStreamsAPI15.model.entity.LapEntity;
import com.foxminded.JavaStreamsAPI15.model.entity.RacerEntity;
import com.foxminded.JavaStreamsAPI15.repository.LapEntityRepository;
import com.foxminded.JavaStreamsAPI15.repository.RacerEntityRepository;
import com.foxminded.JavaStreamsAPI15.service.RacerService;

@Service
public class RacerServiceImpl implements RacerService {

	private final RacerEntityRepository racerEntityRepository;
	private final LapEntityRepository lapEntityRepository;

	public RacerServiceImpl(RacerEntityRepository racerEntityRepository, LapEntityRepository lapEntityRepository) {
		this.racerEntityRepository = racerEntityRepository;
		this.lapEntityRepository = lapEntityRepository;

	}

	@Override
	public List<Racer> findAll() {
		Map<String, List<LapEntity>> lapMap = Stream
				.concat(lapEntityRepository.findAllStarts().stream(), lapEntityRepository.findAllFinishes().stream())
				.collect(Collectors.groupingBy(LapEntity::getId));

		return racerEntityRepository.findAll().stream().map(racer -> mapRacer(racer, lapMap.get(racer.getId())))
				.toList();
	}

	public List<Lap> parseLaps(List<LapEntity> lapEntities) {
		List<LocalDateTime> timeList;
		List<LocalDateTime> startTimeList = new ArrayList<>();
		List<LocalDateTime> finishTimeList = new ArrayList<>();
		List<Lap> lapList = new ArrayList<>();

		timeList = lapEntities.stream().map(lapentity -> lapentity.getTime()).sorted().toList();
		for (LocalDateTime timeElement : timeList) {
			if (timeList.indexOf(timeElement) % 2 == 0) {
				startTimeList.add(timeElement);
			}
			if (timeList.indexOf(timeElement) % 2 != 0 && timeList.indexOf(timeElement) != 0) {
				finishTimeList.add(timeElement);
			}
		}

		if (!startTimeList.isEmpty()) {
			for (int i = 0; i < startTimeList.size(); i++) {
				if (startTimeList.size() > finishTimeList.size() && i == startTimeList.size() - 1) {
					lapList.add(new Lap(startTimeList.get(i), LocalDateTime.MIN));

				} else {
					lapList.add(new Lap(startTimeList.get(i), finishTimeList.get(i)));
				}
			}
		}
		return lapList;
	}

	private Racer mapRacer(RacerEntity racer, List<LapEntity> lapEntities) {
		return new Racer(racer.getName(), racer.getTeam(), parseLaps(lapEntities));
	}
}
