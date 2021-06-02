package com.bpcl.util;

import java.util.concurrent.atomic.AtomicReference;

public class UUIDGeneratorUtil {

	private static AtomicReference<Long> currentTime = new AtomicReference<>(System.currentTimeMillis());

	public static Long longUuidGent() {

		return currentTime.accumulateAndGet(System.currentTimeMillis(), (prev, next) -> next > prev ? next : prev + 1)
				% 100000000000L;

	}

}
