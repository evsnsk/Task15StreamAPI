package com.foxminded.JavaStreamsAPI15.repository;

import com.foxminded.JavaStreamsAPI15.model.entity.LapEntity;

import java.util.Collection;

public interface LapEntityRepository {

	public Collection<LapEntity> findAllStarts();

	public Collection<LapEntity> findAllFinishes();

}
