package me.giorgirokhadze.cryptography;

import java.util.Arrays;

import static me.giorgirokhadze.cryptography.MixColumns.*;
import static me.giorgirokhadze.cryptography.SBox.INV_S;
import static me.giorgirokhadze.cryptography.SBox.S;
import static me.giorgirokhadze.cryptography.Utils.insert;
import static me.giorgirokhadze.cryptography.Utils.xor;

/**
 * Pseudo Random Permutations
 * <p>
 * Created by Giorgi on 4/16/2016.
 */
class PRP {

    static byte[] addRoundKeys(byte[] state, byte[] key) {
        return xor(state, key);
    }

    static byte[] subBytes(byte[] bytes) {
        byte[] result = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++)
            result[i] = (byte) S[bytes[i] & 0xFF];
        return result;
    }

    static byte[] shiftRows(byte[] state) {
        byte[] result = Arrays.copyOf(state, state.length);

        result[1] = state[5];
        result[2] = state[10];
        result[3] = state[15];

        result[5] = state[9];
        result[6] = state[14];
        result[7] = state[3];

        result[9] = state[13];
        result[10] = state[2];
        result[11] = state[7];

        result[13] = state[1];
        result[14] = state[6];
        result[15] = state[11];

        return result;
    }

    static byte[] mixColumns(byte[] state) {
        byte[] result = new byte[state.length];

        for (int i = 0; i < 4; i++)
            insert(result, i * 4, doTheStuff(Arrays.copyOfRange(state, i * 4, i * 4 + 4)));

        return result;
    }

    static byte[] reverseSubBytes(byte[] bytes) {
        byte[] result = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++)
            result[i] = (byte) INV_S[bytes[i] & 0xFF];
        return result;
    }

    static byte[] reverseShiftRows(byte[] bytes) {
        byte[] result = Arrays.copyOf(bytes, bytes.length);

        result[1] = bytes[13];
        result[2] = bytes[10];
        result[3] = bytes[7];

        result[5] = bytes[1];
        result[6] = bytes[14];
        result[7] = bytes[11];

        result[9] = bytes[5];
        result[10] = bytes[2];
        result[11] = bytes[15];

        result[13] = bytes[9];
        result[14] = bytes[6];
        result[15] = bytes[3];

        return result;
    }

    static byte[] reverseMixColumns(byte[] bytes) {
        byte[] result = new byte[bytes.length];

        for (int i = 0; i < 4; i++)
            insert(result, i * 4, doOtherStuff(Arrays.copyOfRange(bytes, i * 4, i * 4 + 4)));

        return result;
    }

    private static byte[] doTheStuff(byte[] bytes) {
        byte[] result = new byte[bytes.length];
        result[0] = (byte) (_2[bytes[0] & 0xFF] ^ _3[bytes[1] & 0xFF] ^ bytes[2] ^ bytes[3]);
        result[1] = (byte) (bytes[0] ^ _2[bytes[1] & 0xFF] ^ _3[bytes[2] & 0xFF] ^ bytes[3]);
        result[2] = (byte) (bytes[0] ^ bytes[1] ^ _2[bytes[2] & 0xFF] ^ _3[bytes[3] & 0xFF]);
        result[3] = (byte) (_3[bytes[0] & 0xFF] ^ bytes[1] ^ bytes[2] ^ _2[bytes[3] & 0xFF]);
        return result;
    }

    private static byte[] doOtherStuff(byte[] bytes) {
        byte[] result = new byte[bytes.length];
        result[0] = (byte) (_14[bytes[0] & 0xFF] ^ _11[bytes[1] & 0xFF] ^ _13[bytes[2] & 0xFF] ^ _9[bytes[3] & 0xFF]);
        result[1] = (byte) (_9[bytes[0] & 0xFF] ^ _14[bytes[1] & 0xFF] ^ _11[bytes[2] & 0xFF] ^ _13[bytes[3] & 0xFF]);
        result[2] = (byte) (_13[bytes[0] & 0xFF] ^ _9[bytes[1] & 0xFF] ^ _14[bytes[2] & 0xFF] ^ _11[bytes[3] & 0xFF]);
        result[3] = (byte) (_11[bytes[0] & 0xFF] ^ _13[bytes[1] & 0xFF] ^ _9[bytes[2] & 0xFF] ^ _14[bytes[3] & 0xFF]);
        return result;
    }

}
