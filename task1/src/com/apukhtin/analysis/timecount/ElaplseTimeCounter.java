package com.apukhtin.analysis.timecount;

public class ElaplseTimeCounter {
	private static long startCountingTime;
	private static boolean isCounting;

	public static void start() {
		startCountingTime = System.currentTimeMillis();
		isCounting = true;
	}

	public static void end() {
		// Not initialized
		if (!isCounting) {
			throw new RuntimeException("Counter has not been started");
		} else {
			System.out.printf("Time elapsed: %d millis", 
					System.currentTimeMillis() - startCountingTime);
			isCounting = false;
		}
	}
}
