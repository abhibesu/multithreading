package com.abhi.mutithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLauncher {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": RunnableTest");
        
        ExecutorService service = Executors.newFixedThreadPool(5);
 
        // Lambda Runnable
        int counter = 0;
        do {
        service.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });
        service.shutdown();
        }while(counter<=4);
        
       
 
    }
}