package com.abhi.mutithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RunnableThreadLauncher {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + ": RunnableTest");

		ExecutorService service = Executors.newSingleThreadExecutor();

		// Lambda Runnable
			service.submit(() -> {
				String threadName = Thread.currentThread().getName();
				System.out.println("Hello " + threadName);
			});
			try {
				System.out.println("attempt to shutdown executor");
				service.shutdown();
				service.awaitTermination(5, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				System.err.println("tasks interrupted");
			} finally {
				if (!service.isTerminated()) {
					System.err.println("cancel non-finished tasks");
				}
				service.shutdownNow();
				System.out.println("shutdown finished");
			}
	}
}