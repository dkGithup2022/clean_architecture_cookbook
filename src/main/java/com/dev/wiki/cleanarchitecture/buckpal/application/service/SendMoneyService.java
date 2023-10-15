package com.dev.wiki.cleanarchitecture.buckpal.application.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.wiki.cleanarchitecture.buckpal.application.port.in.SendMoneyCommand;
import com.dev.wiki.cleanarchitecture.buckpal.application.port.in.SendMoneyUseCase;
import com.dev.wiki.cleanarchitecture.buckpal.application.port.out.LoadAccountPort;
import com.dev.wiki.cleanarchitecture.buckpal.application.port.out.UpdateAccountStatePort;
import com.dev.wiki.cleanarchitecture.buckpal.common.Money;
import com.dev.wiki.cleanarchitecture.buckpal.domain.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class SendMoneyService implements SendMoneyUseCase {

	private final LoadAccountPort loadAccountPort;
	//private final AccontLock accontLock;
	private final UpdateAccountStatePort updateAccountStatePort;

	@Override
	public boolean sendMoney(SendMoneyCommand command) {
		LocalDateTime baselineDate = LocalDateTime.now().minusDays(10);

		Account fromAccount = loadAccountPort.loadAccount(command.fromId(), baselineDate);
		Account toAccount = loadAccountPort.loadAccount(command.toId(), baselineDate);

		// validation 생략
		fromAccount.withdraw(Money.of(command.amount()), toAccount.getId().get());
		toAccount.deposit(Money.of(command.amount()), fromAccount.getId().get());

		updateAccountStatePort.updateActivities(fromAccount);
		updateAccountStatePort.updateActivities(toAccount);
		return false;
	}

}
