package com.dev.wiki.cleanarchitecture.buckpal.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
		// mock domain & raed Port
		Account sourceAccount = givenAnAccountWithId(new Account.AccountId(1L));
		Account destAccount = givenAnAccountWithId(new Account.AccountId(2L));

		// mock write port
		givenUpdateAccountStatePortSucceed();

		//req
		SendMoneyCommand command = new SendMoneyCommand(
			sourceAccount.getId().getValue(),
			destAccount.getId().getValue(),
			500L);

		boolean success = sendMoneyService.sendMoney(command);

		assertTrue(success);
	}

	private Account givenAnAccountWithId(Account.AccountId id) {
		Account account = Mockito.mock(Account.class);
		given(account.getId())
			.willReturn(id);

		given(loadAccountPort.loadAccount(eq(account.getId().getValue()), any(LocalDateTime.class)))
			.willReturn(account);

		return account;
	}

	private void givenUpdateAccountStatePortSucceed() {
		doNothing().when(updateAccountStatePort).updateActivities(any(Account.class));
	}

}