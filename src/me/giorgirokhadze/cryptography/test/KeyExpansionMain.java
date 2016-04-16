package me.giorgirokhadze.cryptography.test;

import me.giorgirokhadze.cryptography.KeyExpansion;

import java.util.Arrays;

class KeyExpansionMain {

    private static byte[] keyOne = {
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0
    };

    private static byte[] keyTwo = {
            -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1
    };

    public static void main(String[] args) {
        KeyExpansion keyExpansionOne = new KeyExpansion(keyOne);
        KeyExpansion keyExpansionTwo = new KeyExpansion(keyTwo);

        byte[][] res;
        res = keyExpansionOne.expand();
        System.out.println(Arrays.deepToString(res));
        res = keyExpansionTwo.expand();
        System.out.println(Arrays.deepToString(res));
    }

}
