package com.foxminded.JavaStreamsAPI15;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.foxminded.JavaStreamsAPI15.service.MenuServise;

@Component
@Profile("!disableMenu")
public class MenuTrigger implements ApplicationRunner {

	private final MenuServise menuServise;

	public MenuTrigger(MenuServise menuServise) {
		this.menuServise = menuServise;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		menuServise.menuStart();
	}

}
