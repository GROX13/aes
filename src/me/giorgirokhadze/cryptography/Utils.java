package me.giorgirokhadze.cryptography;

import java.util.Arrays;

import static me.giorgirokhadze.cryptography.SBox.S;

/**
 * Some utils stuff
 * <p>
 * Created by Giorgi on 4/16/2016.
 */
class Utils {

    static byte[] subWord(byte[] bytes) {
        byte[] result = Arrays.copyOf(bytes, bytes.length);
        for (int i = 0; i < result.length; i++)
            result[i] = (byte) S[result[i] & 0xFF];
        return result;
    }

    static byte[] rotWord(byte[] bytes) {
        byte[] result = Arrays.copyOf(bytes, bytes.length);
        result[0] = bytes[bytes.length - 1];
        result[result.length - 1] = bytes[0];
        return result;
    }

    static byte[] xor(byte[] a, byte[] b) {
        byte[] result = new byte[a.length];
        for (int i = 0; i < a.length; i++)
            result[i] = (byte) (a[i] ^ b[i]);
        return result;
    }

    static byte[] xor(byte[] a, int b) {
        byte[] result = Arrays.copyOf(a, a.length);
        result[0] ^= b;
        return result;
    }
}
