package com.foxminded.JavaStreamsAPI15.repository.impl;

import static org.mockito.Mockito.when;
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

import com.foxminded.JavaStreamsAPI15.model.entity.RacerEntity;

@SpringBootTest(classes = { RacerEntityRepositoryImpl.class })
class RacerEntityRepositoryImplTest {

	@MockBean(name = "racerDataSupplier")
	Supplier<Stream<String>> racerDataSupplier;

	@Autowired
	RacerEntityRepositoryImpl racerEntityRepositoryImpl;

	List<String> SupplierRacerEntityList;

	@BeforeEach
	public void init() {
		SupplierRacerEntityList = new ArrayList<>();
		SupplierRacerEntityList.add("CSM_Carlos Sainz_MCLAREN RENAULT");
	}

	@Test
	void testFindAll_ReturnNotEmptyList() {
		when(racerDataSupplier.get()).thenReturn(SupplierRacerEntityList.stream());
		Assertions.assertThat(racerEntityRepositoryImpl.findAll()).isNotEmpty();
	}

	@Test
	void testFindAll_ReturnCorrectEntity() {
		when(racerDataSupplier.get()).thenReturn(SupplierRacerEntityList.stream());
		Assertions.assertThat(racerEntityRepositoryImpl.findAll().get(0)).usingRecursiveComparison()
				.isEqualTo(new RacerEntity("CSM", "Carlos Sainz", "MCLAREN RENAULT"));
	}

	@Test
	void testFindAll_ReturnMoreThenOneCorrectEntities() {
		SupplierRacerEntityList.add("GRW_George Russell_WILLIAMS MERCEDES");
		List<RacerEntity> raserEntityList = new ArrayList<>();
		raserEntityList.add(new RacerEntity("CSM", "Carlos Sainz", "MCLAREN RENAULT"));
		raserEntityList.add(new RacerEntity("GRW", "George Russell", "WILLIAMS MERCEDES"));

		when(racerDataSupplier.get()).thenReturn(SupplierRacerEntityList.stream());
		Assertions.assertThat(racerEntityRepositoryImpl.findAll()).usingRecursiveComparison()
				.isEqualTo(raserEntityList);
	}

}
