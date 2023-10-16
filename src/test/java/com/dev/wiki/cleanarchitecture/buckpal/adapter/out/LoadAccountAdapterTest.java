package com.dev.wiki.cleanarchitecture.buckpal.adapter.out;

import static org.springframework.test.util.AssertionErrors.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import com.dev.wiki.cleanarchitecture.buckpal.domain.Account;

@DataJpaTest
@Import({UpdateAccountStateAdapter.class, LoadAccountAdapter.class, AccountMapper.class})
class LoadAccountAdapterTest {

	@Autowired
	UpdateAccountStateAdapter updateAccountStateAdapter;

	@Autowired
	LoadAccountAdapter loadAccountAdapter;

	@Autowired
	AccountMapper accountMapper;

	@Test
	@Sql(scripts = {"classpath:sql/accountPersistenceAdapterSql.sql"})
	void 조회() {
		Account account = loadAccountAdapter.loadAccount(1L, LocalDateTime.of(2018, 8, 10, 0, 0));
		System.out.println(account.getBaselineBalance());
		System.out.println(account.getActivityWindow().getActivities().size());
	}
}