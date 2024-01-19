package com.foxminded.JavaStreamsAPI15.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class ResourceFileUtils {

	private ResourceFileUtils() {
	}

	public static List<String> readFileToList(String fileName) {
		return readTextFileFromResources(fileName, Stream::toList);
	}

	public static <T> T readTextFileFromResources(String fileName, Function<Stream<String>, T> action) {
		try {
			Path path = Paths.get(ResourceFileUtils.class.getClassLoader().getResource(fileName).toURI());
			return action.apply(Files.lines(path));
		} catch (Exception e) {
			throw new RuntimeException("Unable to read file " + fileName, e);
		}
	}

}
