package com.bpcl.util;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 
 * 
 * @author swetha
 *
 */
public class UUIDGeneratedUtil {
	private static AtomicReference<Long> currentTime = new AtomicReference<>(System.currentTimeMillis());

	public static Long generateIntUuid() {
		Long uuid = currentTime.accumulateAndGet(System.currentTimeMillis(),
				(prev, next) -> next > prev ? next : prev + 1) % 1000000L;
		return uuid;
	}

	public static String generateStringUuid(String str) {

		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static Long generateLotCode() {
		Long uuid = currentTime.accumulateAndGet(System.currentTimeMillis(),
				(prev, next) -> next > prev ? next : prev + 1) % 1000000L;
		return uuid;
	}

}
