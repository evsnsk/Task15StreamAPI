package com.foxminded.JavaStreamsAPI15.service;

public interface UserInputOutput {

	void print(String s);

	String read();

	default void println(String s) {
		print(s);
		print("\n");
	}

	
	
}
