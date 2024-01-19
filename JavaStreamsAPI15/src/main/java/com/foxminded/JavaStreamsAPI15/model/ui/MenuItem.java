package com.foxminded.JavaStreamsAPI15.model.ui;

public class MenuItem {
	private final int value;
	private final String title;

	private final boolean isDefault;
	private final Runnable action;

	public MenuItem(int value, String title, boolean isDefault, Runnable action) {
		this.value = value;
		this.title = title;
		this.isDefault = isDefault;
		this.action = action;
	}

	public int getValue() {
		return value;
	}

	public String getTitle() {
		return title;
	}

	public Runnable getAction() {
		return action;
	}

	public boolean isDefault() {
		return isDefault;
	}

}
