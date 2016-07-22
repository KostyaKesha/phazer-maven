package com.goitzub.java;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;


public class TestSquare {
    @Test
    public void sumSquare(){

        int[] digits = new int[64];
        Random random = new Random();
        IntStream.range(0, digits.length).forEach(i -> {
            digits[i] = random.nextInt(100);
        });
        long actual = new ImplSumAquar().sumSq(digits,2);

        long expected = 0;
        for (int digit : digits) {
            expected += Math.pow(digit, 2);
        }
        Assert.assertEquals(expected, actual);
    }
}


