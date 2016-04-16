package me.giorgirokhadze.cryptography;

import java.util.Arrays;

import static me.giorgirokhadze.cryptography.SBox.S;
import static me.giorgirokhadze.cryptography.Utils.xor;

/**
 * Pseudo Random Permutations
 * <p>
 * Created by Giorgi on 4/16/2016.
 */
public class PRP {

    public byte[] subBytes(byte[] bytes) {
        byte[] result = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++)
            result[i] = (byte) S[bytes[i] & 0xFF];
        return result;
    }

    public byte[] shiftRows(byte[] state) {
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

    public void mixColumns() {

    }

    public byte[] addRoundKeys(byte[] state, byte[] key) {
        return xor(state, key);
    }

}
