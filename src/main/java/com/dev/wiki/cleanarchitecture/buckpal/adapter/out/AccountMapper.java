package com.dev.wiki.cleanarchitecture.buckpal.adapter.out;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dev.wiki.cleanarchitecture.buckpal.adapter.out.entity.AccountJpaEntity;
import com.dev.wiki.cleanarchitecture.buckpal.adapter.out.entity.ActivityJpaEntity;
import com.dev.wiki.cleanarchitecture.buckpal.common.Money;
import com.dev.wiki.cleanarchitecture.buckpal.domain.Account;
import com.dev.wiki.cleanarchitecture.buckpal.domain.Activity.Activity;
import com.dev.wiki.cleanarchitecture.buckpal.domain.Activity.ActivityWindow;

@Component
public class AccountMapper {

	Account mapToDomain(AccountJpaEntity account,
		List<ActivityJpaEntity> activities,
		Long withdrawalBalance,
		Long depositBalance) {
		Money baselineBalance = Money.of(BigDecimal.ZERO);

		return Account.withId(
			new Account.AccountId(account.getId()),
			baselineBalance,
			mapToActivityWindow(activities));
	}

	private ActivityWindow mapToActivityWindow(List<ActivityJpaEntity> activities) {
		List<Activity> mappedActivities = new ArrayList<>();

		for (ActivityJpaEntity activity : activities) {
			mappedActivities.add(new Activity(
				new Activity.ActivityId(activity.getId()),
				new Account.AccountId(activity.getOwnerAccountId()),
				new Account.AccountId(activity.getSourceAccountId()),
				new Account.AccountId(activity.getTargetAccountId()),
				activity.getTimestamp(),
				Money.of(BigDecimal.valueOf(activity.getAmount()))
			));
		}

		return new ActivityWindow(mappedActivities);
	}

	ActivityJpaEntity mapToJpaEntity(Activity activity) {
		return new ActivityJpaEntity(
			activity.getId() == null ? null : activity.getId().getValue(),
			activity.getTimestamp(),
			activity.getOwnerAccountId().getValue(),
			activity.getSourceAccountId().getValue(),
			activity.getTargetAccountId().getValue(),
			activity.getMoney().getAmount().longValue());
	}


}
