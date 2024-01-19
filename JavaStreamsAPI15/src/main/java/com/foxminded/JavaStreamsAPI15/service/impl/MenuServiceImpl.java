package com.foxminded.JavaStreamsAPI15.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.foxminded.JavaStreamsAPI15.model.Racer;
import com.foxminded.JavaStreamsAPI15.service.MenuServise;
import com.foxminded.JavaStreamsAPI15.table.impl.TableType;
import com.foxminded.JavaStreamsAPI15.service.ConsoleService;
import com.foxminded.JavaStreamsAPI15.service.RacerService;
import com.foxminded.JavaStreamsAPI15.service.UserInputOutput;

@Service
public class MenuServiceImpl implements MenuServise {

	private final ConsoleService consoleService;
	private final RacerService racerService;
	private final UserInputOutput userInputOutput;
	private static final Map<String, TableType> tableTitleMap = new HashMap<>();

	static {
		tableTitleMap.put("Racers best lap", TableType.BestLapTable);
		tableTitleMap.put("Racers names", TableType.NamesTable);
		tableTitleMap.put("Racers lap count", TableType.LapCountTable);
		tableTitleMap.put("Racers avg lap time", TableType.AvgLapTimeTable);
	}

	public MenuServiceImpl(RacerService racerService, ConsoleService consoleService, UserInputOutput userInputOutput) {
		this.consoleService = consoleService;
		this.racerService = racerService;
		this.userInputOutput = userInputOutput;
	}

	@Override
	public void menuStart() {
		List<Racer> racers = racerService.findAll();
		while (true) {
			userInputOutput.println("\nChoose Table:");
			tableTitleMap.keySet().stream().forEach(title -> userInputOutput.print(title + "|"));
			userInputOutput.print("\n");
			String title = userInputOutput.read();
			if (title.equals("exit")) {
				break;
			}
			if (!tableTitleMap.containsKey(title)) {
				userInputOutput.println("Wrong title!");
				continue;
			}

			userInputOutput.print("\n" + "Table type: " + title + "\n");
			userInputOutput.print(consoleService.outputTable(tableTitleMap.get(title), racers));

		}

	}

}
