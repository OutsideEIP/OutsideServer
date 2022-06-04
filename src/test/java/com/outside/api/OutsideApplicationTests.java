package com.outside.api;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class OutsideApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Hello!")));
	}

	@Test
	public void postRequestTwitterFailed() throws Exception {
		String refreshToken = "test";
		mvc.perform(MockMvcRequestBuilders.post("/auth/register/twitter").accept(MediaType.APPLICATION_JSON).param("refreshToken", refreshToken))
				.andExpect(status().is(400))
				.andExpect(content().string(equalTo("The size of the refresh token is not correct")));
	}

	@Test
	public void postRequestTwitterSuccess() throws Exception {
		String refreshToken = "12345678901234567890123456789013";
		mvc.perform(MockMvcRequestBuilders.post("/auth/register/twitter").accept(MediaType.APPLICATION_JSON).param("refreshToken", refreshToken))
				.andExpect(status().is(200))
				.andExpect(content().string(equalTo("You are successfully registered")));
	}

	// @Test
	// public void postLogin() throws Exception {
	// 	mvc.perform(MockMvcRequestBuilders.post("/auth/login").accept(MediaType.APPLICATION_JSON))
	// 			.andExpect(status().isOk())
	// 			.andExpect(content().string(equalTo("login ok")));
	// }

	// @Test
	// public void postRegister() throws Exception {
	// 	mvc.perform(MockMvcRequestBuilders.post("/auth/register").accept(MediaType.APPLICATION_JSON))
	// 			.andExpect(status().isOk())
	// 			.andExpect(content().string(equalTo("register ok")));
	// }
}