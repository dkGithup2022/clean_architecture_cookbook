package com.dev.wiki.cleanarchitecture;

import com.dev.wiki.cleanarchitecture.buckpal.common.Money;
import com.dev.wiki.cleanarchitecture.buckpal.domain.Account;
import com.dev.wiki.cleanarchitecture.buckpal.domain.Activity.ActivityWindow;

public class AccountFixtureFactory {

	public static AccountBuilder account() {
		return new AccountBuilder().id(null).baselineBalance(Money.of(0L)).activityWindow(new ActivityWindow());
	}

	public static class AccountBuilder {
		private Account.AccountId id;

		private Money baselineBalance;

		private ActivityWindow activityWindow;

		public AccountBuilder id(Account.AccountId id) {
			this.id = id;
			return this;
		}

		public AccountBuilder baselineBalance(Money baselineBalance) {
			this.baselineBalance = baselineBalance;
			return this;
		}

		public AccountBuilder activityWindow(ActivityWindow activityWindow) {
			this.activityWindow = activityWindow;
			return this;
		}

		public Account build() {
			return Account.withId(id, baselineBalance, activityWindow);
		}
	}

}
