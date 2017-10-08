package com.abhi.mutithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableThreadLauncherWithFuture {
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		System.out.println(Thread.currentThread().getName() + ": CallableTest");


		ExecutorService service = Executors.newFixedThreadPool(1);
		
		
		Callable<Integer> task = () -> {
		        ConcurrentUtils.sleep(1);
		        return 123;
		};
		
		Future<Integer> future = service.submit(task);

		System.out.println("future done? " + future.isDone());

		Integer result = future.get(2, TimeUnit.SECONDS);

		System.out.println("future done? " + future.isDone());
		System.out.println("result: " + result);
		
		ConcurrentUtils.stop(service);
	}
}
