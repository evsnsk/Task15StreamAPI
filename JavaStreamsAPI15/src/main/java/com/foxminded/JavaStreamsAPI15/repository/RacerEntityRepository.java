package com.foxminded.JavaStreamsAPI15.repository;

import java.util.Collection;

import com.foxminded.JavaStreamsAPI15.model.entity.RacerEntity;

public interface RacerEntityRepository {

	public Collection<RacerEntity> getRacerEntityList();

	public Collection<RacerEntity> findAll();

}
