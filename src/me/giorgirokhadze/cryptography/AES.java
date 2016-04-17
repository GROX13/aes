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
            byte[] bytes = addRoundKeys(Arrays.copyOfRange(input, i * 16, i * 16 + 16), getKey(expanded, 0));
            for (int j = 0; j < 9; j++)
                bytes = addRoundKeys(mixColumns(shiftRows(subBytes(bytes))), getKey(expanded, j + 1));
            bytes = addRoundKeys(shiftRows(subBytes(bytes)), getKey(expanded, 10));
            insert(result, i * 16, bytes);
        }
        return result;
    }

    public static byte[] decrypt(byte[] input, byte[] key) {
        KeyExpansion expansion = new KeyExpansion(key);
        byte[][] expanded = expansion.expand();
        byte[] result = new byte[input.length];

        for (int j = 0; j < input.length / 16; j++) {
            byte[] bytes = addRoundKeys(Arrays.copyOfRange(input, j * 16, j * 16 + 16), getKey(expanded, 10));
            bytes = reverseSubBytes(reverseShiftRows(bytes));
            for (int i = 9; i > 0; i--)
                bytes = reverseSubBytes(reverseShiftRows(reverseMixColumns(addRoundKeys(bytes, getKey(expanded, i)))));

            bytes = addRoundKeys(bytes, getKey(expanded, 0));
            insert(result, j * 16, bytes);
        }

        return removePadding(result);
    }

    private static byte[] getKey(byte[][] expanded, int i) {
        byte[] key = new byte[16];
        for (int j = 0; j < 4; j++)
            System.arraycopy(expanded[4 * i + j], 0, key, 4 * j, 4);
        return key;
    }

    private static byte[] fillWithPadding(byte[] input) {
        byte[] result = Arrays.copyOfRange(input, 0, (input.length / 16 + 1) * 16);
        result[input.length] = (byte) 0xFF;
        return result;
    }

    private static byte[] removePadding(byte[] output) {
        int index = 0;
        for (int i = output.length - 1; i >= 0; i--)
            if (output[i] == -1) {
                index = i;
                break;
            }
        return Arrays.copyOfRange(output, 0, index);
    }

}
