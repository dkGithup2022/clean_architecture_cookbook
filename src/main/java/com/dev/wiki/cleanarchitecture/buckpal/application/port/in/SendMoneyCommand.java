package com.dev.wiki.cleanarchitecture.buckpal.application.port.in;

import java.util.Objects;

public record SendMoneyCommand(Long fromId, Long toId, Long amount) {
	public SendMoneyCommand {
		Objects.requireNonNull(fromId);
		Objects.requireNonNull(toId);
		if (toId.compareTo(0L) < 0)
			throw new RuntimeException("CMD ERROR :음수만큼을 송금하려 함");
	}

}
