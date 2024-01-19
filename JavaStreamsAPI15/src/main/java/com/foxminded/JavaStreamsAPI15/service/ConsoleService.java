package com.foxminded.JavaStreamsAPI15.service;

import java.util.List;

import com.foxminded.JavaStreamsAPI15.model.Racer;
import com.foxminded.JavaStreamsAPI15.table.impl.TableType;

public interface ConsoleService {

	String outputTable(TableType tableType, List<Racer> racers);

}
