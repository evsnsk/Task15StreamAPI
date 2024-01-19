package com.foxminded.JavaStreamsAPI15.service.impl;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.foxminded.JavaStreamsAPI15.service.UserInputOutput;

@Component
public class UserInputOutputDefault implements UserInputOutput, DisposableBean {

	private final Scanner scanner;
	private final PrintStream out;

	@Autowired
	public UserInputOutputDefault() {
		this(System.in, System.out);
	}

	public UserInputOutputDefault(InputStream in, PrintStream out) {
		scanner = new Scanner(in);
		this.out = out;
	}

	@Override
	public void print(String s) {
		out.print(s);
	}

	@Override
	public String read() {
		return scanner.nextLine();
	}

	@Override
	public void destroy() {
		scanner.close();
		out.close();
	}

}
