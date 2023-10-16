package com.dev.wiki.cleanarchitecture.buckpal.adapter.out;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import com.dev.wiki.cleanarchitecture.buckpal.domain.Account;

@DataJpaTest
@Import({UpdateAccountStateAdapter.class, LoadAccountAdapter.class, AccountMapper.class})
class UpdateAccountStateAdapterTest {

	@Autowired
	UpdateAccountStateAdapter updateAccountStateAdapter;

	@Autowired
	LoadAccountAdapter loadAccountAdapter;

	@Autowired
	AccountMapper accountMapper;

	@Test
	@Sql(scripts={"classpath:sql/accountPersistenceAdapterSql.sql"})
	void 업데이트() {
		Account account = loadAccountAdapter.loadAccount(1L, LocalDateTime.of(2018, 8, 10, 0, 0));
	}
}