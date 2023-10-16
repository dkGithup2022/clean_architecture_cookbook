package com.dev.wiki.cleanarchitecture.buckpal.adapter.in;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.wiki.cleanarchitecture.buckpal.application.port.in.SendMoneyCommand;
import com.dev.wiki.cleanarchitecture.buckpal.application.port.in.SendMoneyUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SendMoneyController {

	private final SendMoneyUseCase sendMoneyUseCase;

	@PostMapping(path = "/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
	void sendMoney(
		@PathVariable("sourceAccountId") Long sourceAccountId,
		@PathVariable("targetAccountId") Long targetAccountId,
		@PathVariable("amount") Long amount) {

		SendMoneyCommand command = new SendMoneyCommand(
			sourceAccountId,
			targetAccountId,
			amount);

		sendMoneyUseCase.sendMoney(command);
	}
}
