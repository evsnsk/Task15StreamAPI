package com.foxminded.JavaStreamsAPI15.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.foxminded.JavaStreamsAPI15.model.entity.LapEntity;
import com.foxminded.JavaStreamsAPI15.repository.LapEntityRepository;
import com.foxminded.JavaStreamsAPI15.utils.StringUtils;

@Repository
public class LapEntityRepositoryImpl implements LapEntityRepository {

	Supplier<Stream<String>> startLogSupplier;
	Supplier<Stream<String>> endLogSupplier;

	public LapEntityRepositoryImpl(@Qualifier("startLogSupplier") Supplier<Stream<String>> startLogSupplier,
			@Qualifier("finishLogSupplier") Supplier<Stream<String>> endLogSupplier) {
		this.startLogSupplier = startLogSupplier;
		this.endLogSupplier = endLogSupplier;
	}

	@Override
	public List<LapEntity> findAllStarts() {
		return findAll(startLogSupplier);
	}

	@Override
	public List<LapEntity> findAllFinishes() {
		return findAll(endLogSupplier);
	}

	public List<LapEntity> findAll(Supplier<Stream<String>> logSupplier) {
		List<LapEntity> lapEntityList = new ArrayList<>();
		logSupplier.get().forEach(
				eLog -> lapEntityList.add(new LapEntity(StringUtils.getID(eLog), StringUtils.getDateTime(eLog))));
		return lapEntityList;
	}

}
