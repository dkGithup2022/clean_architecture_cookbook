package com.dev.wiki.cleanarchitecture.buckpal.domain;

import java.time.LocalDateTime;
import java.util.Optional;

import com.dev.wiki.cleanarchitecture.buckpal.common.Money;
import com.dev.wiki.cleanarchitecture.buckpal.domain.Activity.Activity;
import com.dev.wiki.cleanarchitecture.buckpal.domain.Activity.ActivityWindow;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Value;

@Entity
@Table(name = "ACCOUNT")
public class Account {

	@Getter
	private final AccountId id;

	@Getter
	private final Money baselineBalance;

	/**
	 * The window of latest activities on this account.
	 */
	@Getter
	private final ActivityWindow activityWindow;

	public Account(AccountId id, Money baselineBalance, ActivityWindow activityWindow) {
		this.id = id;
		this.baselineBalance = baselineBalance;
		this.activityWindow = activityWindow;
	}

	/**
	 * Creates an {@link Account} entity without an ID. Use to create a new entity that is not yet
	 * persisted.
	 */
	public static Account withoutId(
		Money baselineBalance,
		ActivityWindow activityWindow) {
		return new Account(null, baselineBalance, activityWindow);
	}

	/**
	 * Creates an {@link Account} entity with an ID. Use to reconstitute a persisted entity.
	 */
	public static Account withId(
		AccountId accountId,
		Money baselineBalance,
		ActivityWindow activityWindow) {
		return new Account(accountId, baselineBalance, activityWindow);
	}

	public Optional<AccountId> getId() {
		return Optional.ofNullable(this.id);
	}

	/**
	 * Calculates the total balance of the account by adding the activity values to the baseline balance.
	 */
	public Money calculateBalance() {
		return this.baselineBalance.add(this.activityWindow.calculateBalance(this.id));
	}

	/**
	 * Tries to withdraw a certain amount of money from this account.
	 * If successful, creates a new activity with a negative value.
	 * @return true if the withdrawal was successful, false if not.
	 */
	public boolean withdraw(Money money, AccountId targetAccountId) {

		if (!mayWithdraw(money)) {
			return false;
		}

		Activity withdrawal = new Activity(
			this.id,
			this.id,
			targetAccountId,
			LocalDateTime.now(),
			money);
		this.activityWindow.addActivity(withdrawal);
		return true;
	}

	private boolean mayWithdraw(Money money) {
		return money.minus(this.calculateBalance()).isPositive();
	}

	/**
	 * Tries to deposit a certain amount of money to this account.
	 * If sucessful, creates a new activity with a positive value.
	 * @return true if the deposit was successful, false if not.
	 */
	public boolean deposit(Money money, AccountId sourceAccountId) {
		Activity deposit = new Activity(
			this.id,
			sourceAccountId,
			this.id,
			LocalDateTime.now(),
			money);
		this.activityWindow.addActivity(deposit);
		return true;
	}

	@Value
	public static class AccountId {
		private Long value;
	}

}
