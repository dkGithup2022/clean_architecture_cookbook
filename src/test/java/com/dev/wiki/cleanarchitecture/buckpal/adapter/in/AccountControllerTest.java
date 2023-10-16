package com.dev.wiki.cleanarchitecture.buckpal.adapter.in;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.dev.wiki.cleanarchitecture.buckpal.application.port.in.SendMoneyCommand;
import com.dev.wiki.cleanarchitecture.buckpal.application.port.in.SendMoneyUseCase;

@WebMvcTest(controllers = SendMoneyController.class)
class AccountControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	SendMoneyUseCase sendMoneyUseCase;

	@Test
	void api_호출_성공() throws Exception {
		mvc.perform(post("/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}",
			41L, 42L, 500).header(
			"Content-Type", "application/json"
		)).andExpect(status().isOk());

		then(sendMoneyUseCase).should()
			.sendMoney(eq(new SendMoneyCommand(
				41L,
				42L,
				500L)));
	}

}