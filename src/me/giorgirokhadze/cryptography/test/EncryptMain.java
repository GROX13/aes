package me.giorgirokhadze.cryptography.test;

import me.giorgirokhadze.cryptography.AES;

import java.util.Arrays;

class EncryptMain {

    public static void main(String[] args) {
        @SuppressWarnings("SpellCheckingInspection")
        String test = "asdfghjkasdfghjk";
        byte[] key = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        System.out.println("Finish: ".concat(Arrays.toString(AES.encrypt(test.getBytes(), key))));
        test = "123!@#qweQWEasdASD";
        System.out.println("Finish: ".concat(Arrays.toString(AES.encrypt(test.getBytes(), key))));
    }

}
