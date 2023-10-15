package com.dev.wiki.cleanarchitecture.buckpal.adapter.out;

import org.springframework.stereotype.Component;

import com.dev.wiki.cleanarchitecture.buckpal.adapter.out.entity.ActivityRepository;
import com.dev.wiki.cleanarchitecture.buckpal.application.port.out.UpdateAccountStatePort;
import com.dev.wiki.cleanarchitecture.buckpal.domain.Account;
import com.dev.wiki.cleanarchitecture.buckpal.domain.Activity.Activity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateAccountStateAdapter implements UpdateAccountStatePort {

	private final ActivityRepository activityRepository;
	private final AccountMapper accountMapper;

	@Override
	public void updateActivities(Account account) {
		for (Activity activity : account.getActivityWindow().getActivities()) {
			if (activity.getId() == null) {
				activityRepository.save(accountMapper.mapToJpaEntity(activity));
			}
		}
	}
}
