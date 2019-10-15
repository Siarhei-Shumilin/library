package by.javatr.library.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String md5(String target) {
        StringBuilder sb;
        try {
            byte[] data = MessageDigest.getInstance("MD5").digest(target.getBytes("UTF-8"));
            sb = new StringBuilder();
            for (byte aData : data) {
                sb.append(Integer.toHexString((aData & 0xFF) | 0x100).substring(1, 3));
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
