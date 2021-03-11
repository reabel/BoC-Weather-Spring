package com.reabel.weather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class WeatherApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void getRoot() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk());
	}

	@Test
	public void getRecord() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/record?id=1")).andExpect(status().isOk());
	}

	@Test
	public void getNonExistingRecord() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/record?id=-1")).andExpect(status().isNotFound());
	}

	@Test
	public void getDateFilter() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/filter?dateFrom=20/09/2018&dateTo=21/10/2020"))
				.andExpect(status().isOk());
	}

	@Test
	public void getDateFilterWithoutTo() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/filter?dateFrom=20/09/2018")).andExpect(status().isBadRequest());
	}
}
