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
	public void getTable() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/table")).andExpect(status().isOk());
	}
}
