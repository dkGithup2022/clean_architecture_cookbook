package com.dev.wiki.cleanarchitecture.buckpal.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.dev.wiki.cleanarchitecture.AccountFixtureFactory;
import com.dev.wiki.cleanarchitecture.buckpal.application.port.in.SendMoneyCommand;
import com.dev.wiki.cleanarchitecture.buckpal.application.port.out.LoadAccountPort;
import com.dev.wiki.cleanarchitecture.buckpal.application.port.out.UpdateAccountStatePort;
import com.dev.wiki.cleanarchitecture.buckpal.common.Money;
import com.dev.wiki.cleanarchitecture.buckpal.domain.Account;

class SendMoneyServiceTest {

	private final LoadAccountPort loadAccountPort =
		Mockito.mock(LoadAccountPort.class);

	private final UpdateAccountStatePort updateAccountStatePort =
		Mockito.mock(UpdateAccountStatePort.class);

	private final SendMoneyService sendMoneyService =
		new SendMoneyService(loadAccountPort, updateAccountStatePort);

	@Test
	public void 송금_유즈케이스_성공() {
		Account source = givenSourceAccount();
		Account dest = givenDestAccount();


		SendMoneyCommand cmd = new SendMoneyCommand(
			source.getId().getValue(),
			dest.getId().getValue(),
			500L);




		sendMoneyService.sendMoney(cmd);

	}


	private void givenWithdrawalWillSucceed(Account account) {
		given(account.withdraw(any(Money.class), any(Account.AccountId.class)))
			.willReturn(true);
	}

	Account givenSourceAccount(){
		return AccountFixtureFactory.account()
			.id(new Account.AccountId(1L))
			.baselineBalance(Money.of(10000L))
			.build();
	}

	Account givenDestAccount(){
		return AccountFixtureFactory.account()
			.id(new Account.AccountId(2L))
			.build();
	}



}