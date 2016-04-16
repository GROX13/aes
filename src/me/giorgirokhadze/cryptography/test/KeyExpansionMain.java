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

    private static byte[] keyThree = {
            1, 2, 3, 4, 5, 6, 7, 8,
            9, 10, 11, 12, 13, 14, 15, 16
    };

    public static void main(String[] args) {
        KeyExpansion keyExpansionOne = new KeyExpansion(keyOne);
        KeyExpansion keyExpansionTwo = new KeyExpansion(keyTwo);
        KeyExpansion keyExpansionThree = new KeyExpansion(keyThree);

        byte[][] res;
        res = keyExpansionOne.expand();
        System.out.println(Arrays.deepToString(res));
        res = keyExpansionTwo.expand();
        System.out.println(Arrays.deepToString(res));
        res = keyExpansionThree.expand();
        System.out.println(Arrays.deepToString(res));
    }

}
