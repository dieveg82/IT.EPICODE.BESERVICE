package it.epicode;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	void findAllComunePage() throws Exception {
		mockMvc.perform(get("/controllercomune/findcomunepage")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Albiano")));
	}
	
	@Test
	void findAllComune() throws Exception {
		mockMvc.perform(get("/controllercomune/findallpage")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void removeComune() throws Exception {
		mockMvc.perform(get("/controllercomune/removecomune?id=1")).andDo(print()).andExpect(status().isOk());
	}
}
