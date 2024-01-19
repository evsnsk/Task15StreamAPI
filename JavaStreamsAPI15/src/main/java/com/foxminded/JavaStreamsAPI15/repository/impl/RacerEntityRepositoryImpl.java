package com.foxminded.JavaStreamsAPI15.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.foxminded.JavaStreamsAPI15.model.entity.RacerEntity;
import com.foxminded.JavaStreamsAPI15.repository.*;
import com.foxminded.JavaStreamsAPI15.utils.StringUtils;

@Repository
public class RacerEntityRepositoryImpl implements RacerEntityRepository {

	private List<RacerEntity> racerEntityList;
	final Supplier<Stream<String>> racerDataSupplier;

	public RacerEntityRepositoryImpl(@Qualifier("racerDataSupplier") Supplier<Stream<String>> racerDataSupplier) {
		racerEntityList = new ArrayList<>();
		this.racerDataSupplier = racerDataSupplier;
	}

	@Override
	public List<RacerEntity> getRacerEntityList() {
		return this.findAll();
	}

	@Override
	public List<RacerEntity> findAll() {
		racerDataSupplier.get().forEach(racer -> racerEntityList.add(
				new RacerEntity(StringUtils.getID(racer), StringUtils.getName(racer), StringUtils.getTeam(racer))));
		return racerEntityList;
	}

}
