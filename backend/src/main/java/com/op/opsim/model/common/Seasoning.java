package com.op.opsim.model.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Seasoning {

    private Random random = new Random();

    private final List<Character> DEFAULT_ALPHABET;

    public Seasoning() {
        DEFAULT_ALPHABET = new ArrayList<>(128);
        for (char c = 'A'; c <= 'Z'; c++) {
            DEFAULT_ALPHABET.add(c);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            DEFAULT_ALPHABET.add(c);
        }
        for (char c = '0'; c <= '9'; c++) {
            DEFAULT_ALPHABET.add(c);
        }
        DEFAULT_ALPHABET.add('_');
    }

    public String generateSalt(int saltLength) {
        return generateSalt(saltLength, DEFAULT_ALPHABET);
    }

    public String generateSalt(int saltLength, List<Character> alphabet) {
        StringBuilder sb = new StringBuilder();
        int alphabetSize = alphabet.size();
        for (int i = 0; i < saltLength; i++) {
            int j = random.nextInt(alphabetSize);
            char c = alphabet.get(j);
            sb.append(c);
        }
        return sb.toString();
    }
}
