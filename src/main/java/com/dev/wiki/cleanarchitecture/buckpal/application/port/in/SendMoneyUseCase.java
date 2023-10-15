package com.dev.wiki.cleanarchitecture.buckpal.application.port.in;

public interface SendMoneyUseCase {

	boolean sendMoney(SendMoneyCommand command);
}
