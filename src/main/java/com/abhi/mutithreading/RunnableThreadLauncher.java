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

import com.abhi.mutithreading.CallableThreadLauncher.FactorialCalculator;

public class RunnableThreadLauncher {
	public static class FactorialCalculator implements Runnable {

		private Integer number;

		public FactorialCalculator(Integer number) {
			this.number = number;
		}

		@Override
		public void run() {
			System.out.println("For number :" + number + " Starting time: " + System.currentTimeMillis()
					+ " , Starting Thread:" + Thread.currentThread().getName());
			int result = 1;
			if ((number == 0) || (number == 1)) {
				result = 1;
			} else {
				for (int i = 2; i <= number; i++) {
					result *= i;
					try {
						TimeUnit.MILLISECONDS.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				}
			}
			System.out.println("Result for number - " + number + " -> " + result + " Ending time: "
					+ System.currentTimeMillis() + " , Starting Thread:" + Thread.currentThread().getName());
		}
	}

	public static void main(String[] args) {
		// ThreadPoolExecutor executor = (ThreadPoolExecutor)
		// Executors.newFixedThreadPool(2);
		ExecutorService executor = Executors.newFixedThreadPool(4);
		List<Future<Integer>> resultList = new ArrayList<>();

		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			Integer number = random.nextInt(10);
			FactorialCalculator calculator = new FactorialCalculator(number);
			executor.submit(calculator);
		}

		// shut down the executor service now
		executor.shutdown();
	}
}