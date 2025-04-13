package ru.itis.generics.ex12;

import java.math.BigInteger;

public class NewInteger extends BigInteger {
    public NewInteger(byte[] val, int off, int len) {
        super(val, off, len);
    }
}
