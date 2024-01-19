package com.foxminded.JavaStreamsAPI15.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
	String basicInput = "CSM_Carlos Sainz_MCLAREN RENAULT";
	String emptyString = "";

	@Test
	void testGetID() {
		String id = "CSM";
		Assertions.assertThat(StringUtils.getID(basicInput)).isNotNull();
		Assertions.assertThat(StringUtils.getID(basicInput)).isEqualTo(id);
	}

	@Test
	void testGetID_NullString() {
		Assertions.assertThat(StringUtils.getID(null)).isNotNull();
		Assertions.assertThat(StringUtils.getID(null)).isEmpty();
		;
	}

	@Test
	void testGetID_EmptyString() {
		Assertions.assertThat(StringUtils.getID(emptyString)).isNotNull();
		Assertions.assertThat(StringUtils.getID(emptyString)).isEmpty();
	}

	@Test
	void testGetID_UnsuitableStringLength() {
		Assertions.assertThat(StringUtils.getID("aa")).isNotNull();
		Assertions.assertThat(StringUtils.getID("aa")).isEmpty();
	}

	@Test
	void testGetName() {
		String name = "Carlos Sainz";
		Assertions.assertThat(StringUtils.getName(basicInput)).isNotNull();
		Assertions.assertThat(StringUtils.getName(basicInput)).isEqualTo(name);
	}

	@Test
	void testGetName_NullString() {
		Assertions.assertThat(StringUtils.getName(null)).isNotNull();
		Assertions.assertThat(StringUtils.getName(null)).isEmpty();
	}

	@Test
	void testGetName_EmptyString() {
		Assertions.assertThat(StringUtils.getName(emptyString)).isNotNull();
		Assertions.assertThat(StringUtils.getName(emptyString)).isEmpty();
	}

	@Test
	void testGetName_DoesntContainUnderscore() {
		Assertions.assertThat(StringUtils.getName("CSM Carlos Sainz")).isNotNull();
		Assertions.assertThat(StringUtils.getName("CSM Carlos Sainz")).isEmpty();
	}

	@Test
	void testGetTeam() {
		String team = "MCLAREN RENAULT";
		Assertions.assertThat(StringUtils.getTeam(basicInput)).isNotNull();
		Assertions.assertThat(StringUtils.getTeam(basicInput)).isEqualTo(team);
	}

	@Test
	void testGetTeam_NullString() {
		Assertions.assertThat(StringUtils.getTeam(null)).isNotNull();
		Assertions.assertThat(StringUtils.getTeam(null)).isEmpty();
	}

	@Test
	void testGetTeam_EmptyString() {
		Assertions.assertThat(StringUtils.getTeam(emptyString)).isNotNull();
		Assertions.assertThat(StringUtils.getTeam(emptyString)).isEmpty();
	}

	@Test
	void testGetTeam_DoesntContainUnderscore() {
		Assertions.assertThat(StringUtils.getTeam("CSM Carlos Sainz MCLAREN RENAULT")).isNotNull();
		Assertions.assertThat(StringUtils.getTeam("CSM Carlos Sainz MCLAREN RENAULT")).isEmpty();
	}

	@Test
	void testDateTime() {
		Assertions.assertThat(StringUtils.getDateTime("LHM2019-07-12_12:14:47.144")).isNotNull();
		Assertions.assertThat(StringUtils.getDateTime("LHM2019-07-12_12:14:47.144"))
				.isEqualTo(LocalDateTime.parse("2019-07-12_12:14:47.144", formatter));
	}

	@Test
	void testDateTime_NullString() {
		Assertions.assertThat(StringUtils.getDateTime(null)).isNotNull();
		Assertions.assertThat(StringUtils.getDateTime(null)).isEqualTo(LocalDateTime.MIN);
	}

	@Test
	void testDateTime_EmptyString() {
		Assertions.assertThat(StringUtils.getDateTime(emptyString)).isNotNull();
		Assertions.assertThat(StringUtils.getDateTime(emptyString)).isEqualTo(LocalDateTime.MIN);
	}

	@Test
	void testGetDateTime_UnsuitableStringLength() {
		Assertions.assertThat(StringUtils.getDateTime("aa")).isNotNull();
		Assertions.assertThat(StringUtils.getDateTime("aa")).isEqualTo(LocalDateTime.MIN);
	}

}
