package com.abhi.mutithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallableThreadLauncher {

	public static class FactorialCalculator implements Callable<Integer> {

		private Integer number;

		public FactorialCalculator(Integer number) {
			this.number = number;
		}

		@Override
		public Integer call() throws Exception {
			System.out.println("For number :" + number + " Starting time: " + System.currentTimeMillis()
					+ " , Starting Thread:" + Thread.currentThread().getName());
			int result = 1;
			if ((number == 0) || (number == 1)) {
				result = 1;
			} else {
				for (int i = 2; i <= number; i++) {
					result *= i;
					TimeUnit.SECONDS.sleep(4);
				}
			}
			System.out.println("Result for number - " + number + " -> " + result + " Ending time: "
					+ System.currentTimeMillis() + " , Starting Thread:" + Thread.currentThread().getName());
			return result;
		}
	}

	public static void main(String[] args) {
		//ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		ExecutorService executor = Executors.newFixedThreadPool(4);
		List<Future<Integer>> resultList = new ArrayList<>();

		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			Integer number = random.nextInt(10);
			FactorialCalculator calculator = new FactorialCalculator(number);
			Future<Integer> result = executor.submit(calculator);
			resultList.add(result);
		}

		for (Future<Integer> future : resultList) {
			try {
				System.out.println(
						"Future result is - " + " - " + future.get(3, TimeUnit.SECONDS));
			} catch (InterruptedException | ExecutionException|TimeoutException e) {
				e.printStackTrace();
			}
			try {
				System.out.println(
						"Future result is - " + " - " + future.get() + "; And Task done is " + future.isDone());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		// shut down the executor service now
		executor.shutdownNow();
	}
}
