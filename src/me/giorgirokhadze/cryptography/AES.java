package me.giorgirokhadze.cryptography;

/**
 * The Advanced Encryption Standard (AES).
 * <p>
 * Created by Giorgi on 4/16/2016.
 */
public class AES {

    public byte[] encrypt(byte[] input, byte[] key) {
        byte[] result = new byte[input.length];
        KeyExpansion expansion = new KeyExpansion(key);
        byte[][] expanded = expansion.expand();

        return result;
    }

    public byte[] decrypt(byte[] input, byte[] key) {
        return new byte[input.length];
    }

}
