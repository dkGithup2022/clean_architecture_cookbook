package com.dev.wiki.cleanarchitecture.buckpal.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.dev.wiki.cleanarchitecture.AccountFixtureFactory;
import com.dev.wiki.cleanarchitecture.buckpal.common.Money;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class AccountTest {

	@Test
	void 출금_성공() {
		Account account1 = AccountFixtureFactory.account()
			.baselineBalance(Money.of(10000L))
			.id(new Account.AccountId(1L)).build();

		Account account2 = AccountFixtureFactory.account()
			.baselineBalance(Money.of(0L))
			.id(new Account.AccountId(2L)).build();

		boolean success = account1.withdraw(Money.of(500L), account2.getId());

		assertTrue(success);
		assertEquals(BigDecimal.valueOf(9500L), account1.calculateBalance().getAmount());
	}

	@Test
	void 입금_성공() {
		Account account1 = AccountFixtureFactory.account()
			.baselineBalance(Money.of(10L))
			.id(new Account.AccountId(1L)).build();

		Account account2 = AccountFixtureFactory.account()
			.baselineBalance(Money.of(0L))
			.id(new Account.AccountId(2L)).build();

		boolean success = account1.deposit(Money.of(1L), account2.getId());

		assertTrue(success);
		assertEquals(BigDecimal.valueOf(11L), account1.calculateBalance().getAmount());

	}

}