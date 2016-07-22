package com.goitzub.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * Created by steady on 31.05.16.
 */
public class ImplSumAquar implements Squar {

    private List<Callable<Integer>> tasks = new ArrayList<>();
    private final Phaser phaser = new Phaser();
    private ExecutorService executor;
    private List<Future<Integer>> results;
    private int summa;

    @Override
    public int sumSq(int[] val, int threads) {
        IntStream.range(0, threads).forEach(i -> {
            tasks.add(() -> {
                phaser.register();

                int avrgSum = 0;
                int start = (val.length * i) / threads;
                int end = (val.length * (i + 1)) / threads;
                for (int j = start; j < end; j++) {
                    summa += Math.pow(val[j], 2);
                }

                phaser.arriveAndAwaitAdvance();
                phaser.arriveAndDeregister();

                return avrgSum;

            });

        });
        executor = Executors.newFixedThreadPool(threads);
        try {
            results = executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Future future : results) {
            try {
                summa += (int)future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();

        return summa;

    }
}
