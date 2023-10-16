package com.dev.wiki.cleanarchitecture.buckpal.domain.Activity;

import java.time.LocalDateTime;

import com.dev.wiki.cleanarchitecture.buckpal.common.Money;
import com.dev.wiki.cleanarchitecture.buckpal.domain.Account;

import lombok.Getter;
import lombok.NonNull;
import lombok.Value;


public class Activity {
	@Getter
	private ActivityId id;

	/**
	 * The account that owns this activity.
	 */
	@Getter
	@NonNull
	private final Account.AccountId ownerAccountId;

	/**
	 * The debited account.
	 */
	@Getter
	@NonNull
	private final Account.AccountId sourceAccountId;

	/**
	 * The credited account.
	 */
	@Getter
	@NonNull
	private final Account.AccountId targetAccountId;

	/**
	 * The timestamp of the activity.
	 */
	@Getter
	@NonNull
	private final LocalDateTime timestamp;

	/**
	 * The money that was transferred between the accounts.
	 */
	@Getter
	@NonNull
	private final Money money;

	public Activity(
		Activity.ActivityId activityId,
		@NonNull Account.AccountId ownerAccountId,
		@NonNull Account.AccountId sourceAccountId,
		@NonNull Account.AccountId targetAccountId,
		@NonNull LocalDateTime timestamp,
		@NonNull Money money) {
		this.id = activityId;
		this.ownerAccountId = ownerAccountId;
		this.sourceAccountId = sourceAccountId;
		this.targetAccountId = targetAccountId;
		this.timestamp = timestamp;
		this.money = money;
	}

	public Activity(
		@NonNull Account.AccountId ownerAccountId,
		@NonNull Account.AccountId sourceAccountId,
		@NonNull Account.AccountId targetAccountId,
		@NonNull LocalDateTime timestamp,
		@NonNull Money money) {
		this.id = null;
		this.ownerAccountId = ownerAccountId;
		this.sourceAccountId = sourceAccountId;
		this.targetAccountId = targetAccountId;
		this.timestamp = timestamp;
		this.money = money;
	}

	@Value
	public static class ActivityId {
		private final Long value;
	}
}
