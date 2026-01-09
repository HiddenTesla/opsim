package com.op.opsim.model.common;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Digest {

    public static String sha1sum(String original) {
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            sha1.reset();
            sha1.update(original.getBytes("UTF-8"));
            return new BigInteger(1, sha1.digest()).toString();
        }
        catch (Exception e) {
            return "000000";
        }
    }
}
