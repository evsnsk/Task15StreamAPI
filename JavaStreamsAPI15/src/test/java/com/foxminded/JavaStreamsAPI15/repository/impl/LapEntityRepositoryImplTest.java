package com.foxminded.JavaStreamsAPI15.repository.impl;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.foxminded.JavaStreamsAPI15.model.entity.LapEntity;

@SpringBootTest(classes = { LapEntityRepositoryImpl.class })
class LapEntityRepositoryImplTest {

	@MockBean(name = "startLogSupplier")
	Supplier<Stream<String>> startLogSupplier;
	@MockBean(name = "finishLogSupplier")
	Supplier<Stream<String>> endLogSupplier;

	@Autowired
	LapEntityRepositoryImpl lapEntityRepositoryImpl;

	List<String> startLogSupplierList;
	List<String> endLogSupplierList;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

	@BeforeEach
	public void init() {
		startLogSupplierList = new ArrayList<>();
		startLogSupplierList.add("VBM2019-07-12_12:23:42.474");

		endLogSupplierList = new ArrayList<>();
		endLogSupplierList.add("VBM2019-07-12_12:23:42.474");
	}

	@Test
	void testfindAllStarts_ReturnNotEmptyList() {
		when(startLogSupplier.get()).thenReturn(startLogSupplierList.stream());
		Assertions.assertThat(lapEntityRepositoryImpl.findAll(startLogSupplier)).isNotEmpty();
	}

	@Test
	void testfindAllFinishes_ReturnNotEmptyList() {
		when(endLogSupplier.get()).thenReturn(endLogSupplierList.stream());
		Assertions.assertThat(lapEntityRepositoryImpl.findAll(endLogSupplier)).isNotEmpty();
	}

	@Test
	void testfindAll_ReturnCorrectList() {
		startLogSupplierList.add("VBM2019-07-12_12:27:30.724");
		endLogSupplierList.add("VBM2019-07-12_12:27:30.724");
		List<LapEntity> lapEntityList = new ArrayList<>();

		when(startLogSupplier.get()).thenReturn(startLogSupplierList.stream());
		when(endLogSupplier.get()).thenReturn(endLogSupplierList.stream());

		lapEntityList.add(new LapEntity("VBM", LocalDateTime.parse("2019-07-12_12:23:42.474", formatter)));
		lapEntityList.add(new LapEntity("VBM", LocalDateTime.parse("2019-07-12_12:27:30.724", formatter)));

		Assertions.assertThat(lapEntityRepositoryImpl.findAll(startLogSupplier)).usingRecursiveComparison()
				.isEqualTo(lapEntityList);
		Assertions.assertThat(lapEntityRepositoryImpl.findAll(endLogSupplier)).usingRecursiveComparison()
				.isEqualTo(lapEntityList);

	}

}
