package com.dev.wiki.cleanarchitecture.buckpal.adapter.out;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.dev.wiki.cleanarchitecture.buckpal.adapter.out.entity.AccountJpaEntity;
import com.dev.wiki.cleanarchitecture.buckpal.adapter.out.entity.AccountRepository;
import com.dev.wiki.cleanarchitecture.buckpal.adapter.out.entity.ActivityJpaEntity;
import com.dev.wiki.cleanarchitecture.buckpal.adapter.out.entity.ActivityRepository;
import com.dev.wiki.cleanarchitecture.buckpal.application.port.out.LoadAccountPort;
import com.dev.wiki.cleanarchitecture.buckpal.domain.Account;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AccountPersistenceAdapter implements LoadAccountPort {

	private final AccountRepository accountRepository;
	private final ActivityRepository activityRepository;
	private final AccountMapper accountMapper;

	@Override
	public Account loadAccount(Long id, LocalDateTime baselineDate) {
		AccountJpaEntity accountJpaEntity = accountRepository.findById(id).orElseThrow(
			() -> new RuntimeException("account id 존재하지 않음 ")
		);
		return accountMapper.mapToDomain(
			accountJpaEntity,
			activityRepository.findByOwnerSince(id, baselineDate)
			, 0L, 0L
		);
	}
}
