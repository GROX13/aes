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
        encrypted = new byte[]{
                43, 106, 124, 99, 9, 31, 23, 10,
                121, 86, 60, 109, 52, 62, -80, 44,
                81, 23, -99, -61, 57, -126, 86, 44,
                25, -33, -123, -3, 61, 69, -87, -121
        };
        System.out.println("Finish: ".concat(new String(AES.decrypt(encrypted, key))));
    }

}
