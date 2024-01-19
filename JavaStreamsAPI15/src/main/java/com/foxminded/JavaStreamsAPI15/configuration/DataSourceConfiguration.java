package com.foxminded.JavaStreamsAPI15.configuration;

import com.foxminded.JavaStreamsAPI15.utils.ResourceFileUtils;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguration {

	@Bean("startLogSupplier")
	Supplier<Stream<String>> startLogSupplier() {
		return () -> ResourceFileUtils.readFileToList("files/start.log").stream();
	}

	@Bean("finishLogSupplier")
	Supplier<Stream<String>> finishLogSupplier() {
		return () -> ResourceFileUtils.readFileToList("files/end.log").stream();
	}

	@Bean("racerDataSupplier")
	Supplier<Stream<String>> racerDataSupplier() {
		return () -> ResourceFileUtils.readFileToList("files/abbreviations.txt").stream();
	}

}