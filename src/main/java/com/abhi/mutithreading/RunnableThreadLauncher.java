package com.abhi.mutithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableThreadLauncher {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + ": RunnableTest");

		ExecutorService service = Executors.newSingleThreadExecutor();

		// Lambda Runnable
			service.submit(() -> {
				String threadName = Thread.currentThread().getName();
				System.out.println("Hello " + threadName);
			});
			ConcurrentUtils.stop(service);
	}
}