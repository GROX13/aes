package me.giorgirokhadze.cryptography;

import java.util.Arrays;

import static me.giorgirokhadze.cryptography.PRP.*;
import static me.giorgirokhadze.cryptography.Utils.insert;

/**
 * The Advanced Encryption Standard (AES).
 * <p>
 * Created by Giorgi on 4/16/2016.
 */
public class AES {

    public static byte[] encrypt(byte[] input, byte[] key) {
        KeyExpansion expansion = new KeyExpansion(key);
        byte[][] expanded = expansion.expand();
        input = fillWithPadding(input);
        byte[] result = new byte[input.length];

        for (int i = 0; i < input.length / 16; i++) {
            byte[] tmp = addRoundKeys(Arrays.copyOfRange(input, i * 16, i * 16 + 16), getKey(expanded, 0));
            for (int j = 0; j < 9; j++)
                tmp = addRoundKeys(mixColumns(shiftRows(subBytes(tmp))), getKey(expanded, j + 1));
            tmp = addRoundKeys(shiftRows(subBytes(tmp)), getKey(expanded, 10));
            insert(result, i * 16, tmp);
        }
        return result;
    }

    private static byte[] getKey(byte[][] expanded, int i) {
        byte[] key = new byte[16];
        for (int j = 0; j < 4; j++)
            System.arraycopy(expanded[4 * i + j], 0, key, 4 * j, 4);
        return key;
    }

    public static byte[] decrypt(byte[] input, byte[] key) {
        return new byte[input.length];
    }

    private static byte[] fillWithPadding(byte[] input) {
        byte[] result = Arrays.copyOfRange(input, 0, (input.length / 16 + 1) * 16);
        result[input.length] = (byte) 0xFF;
        return result;
    }


}
