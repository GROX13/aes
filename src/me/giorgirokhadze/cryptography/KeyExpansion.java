package me.giorgirokhadze.cryptography;

import static me.giorgirokhadze.cryptography.Utils.*;

/**
 * Takes as input a key and produces a linear array of Nb * (Nr+1) words.
 * Expanded key provide a Nb word round key for the initial AddRoundKey() stage
 * and for each of the Nr rounds of the cipher. The key is first copied into the
 * first Nb words, the remainder of the expanded key is filled Nb words at a time.
 * <p>
 * Created by Giorgi on 4/16/2016.
 */
public class KeyExpansion {

    private byte[] key;

    public KeyExpansion(byte[] key) {
        this.key = key;
    }

    public byte[][] expand() {
        byte[][] result = new byte[44][4];
        for (int i = 0; i < 4; i++)
            result[i] = new byte[]{key[4 * i], key[4 * i + 1], key[4 * i + 2], key[4 * i + 3]};

        for (int i = 4; i < 44; i++)
            result[i] = xor(result[i - 4],
                    i % 4 == 0 ? xor(subWord(rotWord(result[i - 1])), Rcon.RCON[i / 4]) : result[i - 1]);

        return result;
    }

}
