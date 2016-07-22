package com.goitzub.java;

import java.util.Random;

/**
 * Created by steady on 31.05.16.
 */
public class Realeas {
    public static void main(String[] args) {
        int [] arr = new int[64];
        Random random =new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i]=random.nextInt();
        }
        int result = new ImplSumAquar().sumSq(arr,2);
        System.out.println(result);

    }
}
