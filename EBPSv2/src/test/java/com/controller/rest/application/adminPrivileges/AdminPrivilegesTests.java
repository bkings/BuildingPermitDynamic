/*package com.controller.rest.application.adminPrivileges;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.SpringBootWebApplication;

@RunWith(SpringRunner.class)
//@WebMvcTest
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SpringBootWebApplication.class)
@AutoConfigureMockMvc
class AdminPrivilegesTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void contextLoads() throws Exception {
		assertThat(mvc).isNotNull();
	}

	@Test
	public void testGet() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get("/api/admin/formData/{applicationNo}", "77780").param("formId", "2")).andDo(print()).andExpect(status()
				.isBadRequest());
	}

}
*/