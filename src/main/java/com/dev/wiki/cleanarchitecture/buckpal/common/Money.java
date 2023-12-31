package com.dev.wiki.cleanarchitecture.buckpal.common;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Money {

	private BigDecimal amount;

	private Money(BigDecimal amount) {
		this.amount = amount;
	}

	public static Money of(BigDecimal amount) {
		return new Money(amount);
	}

	public static Money of(Long amount) {
		return Money.of(BigDecimal.valueOf(amount));
	}

	public Money add(Money amount) {
		return Money.of(this.getAmount().add(amount.getAmount()));
	}

	public Money minus(Money amount) {
		return Money.of(this.getAmount().subtract(amount.getAmount()));
	}

	public boolean isPositive() {
		return this.amount.compareTo(BigDecimal.ZERO) >= 0;
	}

	public boolean isEquals(Money money) {
		return this.amount.compareTo(money.amount) == 0;
	}
}
