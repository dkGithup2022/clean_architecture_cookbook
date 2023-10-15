package com.dev.wiki.cleanarchitecture.buckpal.application.port.out;

import java.time.LocalDateTime;

import com.dev.wiki.cleanarchitecture.buckpal.domain.Account;

public interface LoadAccountPort {
	Account loadAccount(Long id, LocalDateTime baselineDate);
}
