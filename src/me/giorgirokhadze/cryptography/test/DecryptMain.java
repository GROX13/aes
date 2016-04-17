package me.giorgirokhadze.cryptography.test;

import me.giorgirokhadze.cryptography.AES;

public class DecryptMain {

    public static void main(String[] args) {
        byte[] encrypted = {
                60, -105, 70, 75, 123, 15, -61, 51,
                -110, -103, 16, -102, -47, -81, 67, -101,
                -53, 4, 79, 64, 7, -100, 33, -25,
                114, 93, -3, -115, -25, -43, -24, -116
        };
        byte[] key = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        System.out.println("Finish: ".concat(new String(AES.decrypt(encrypted, key))));
    }

}
