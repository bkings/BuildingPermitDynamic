/*package com.controller.rest.application.adminPrivileges;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { SpringBootWebApplication.class, HibernateUtilRestController.class })
//@ContextConfiguration(classes = { HibernateUtil.class })
//@AutoConfigureMockMvc
class AdminPrivilegesTests {

	@Autowired
	private MockMvc mvc;

	private String GET_API = "/api/admin/formData/{applicationNo}";
	
	@BeforeEach
	public void loadConfig() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/HibernateUtil")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(mvc).isNotNull();
	}

	@Test
	public void testGet() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.get(GET_API, "77780").param("formId", "2"))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testIfListEmpty() throws Exception {
//		mvc.perform(MockMvcRequestBuilders.get(GET_API,"7778000009").param("formId", "2"))
//			.andDo(print())
//			.andExpect(status().isOk());
		MvcResult res = mvc.perform(MockMvcRequestBuilders.get(GET_API,"7778000009").param("formId", "2")).andReturn();
		assertEquals(200, res.getResponse().getStatus());
	}

}*/
