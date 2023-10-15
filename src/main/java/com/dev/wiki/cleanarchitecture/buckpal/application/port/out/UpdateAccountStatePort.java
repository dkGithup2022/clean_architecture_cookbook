package com.dev.wiki.cleanarchitecture.buckpal.application.port.out;

import com.dev.wiki.cleanarchitecture.buckpal.domain.Account;

public interface UpdateAccountStatePort {
	void updateActivities(Account account);
}
