package org.mixer2.cacheable.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtils {

    public static String sha1Hex(String target) {
        if (target == null) {
            return null;
        }
        byte[] bytes = target.getBytes(Charset.forName("UTF-8"));
        String result = byteDigestToString(getDigest("SHA-1").digest(bytes));
        return result;
    }

    /**
     * バイト列を16進数に変換
     */
    private static String byteDigestToString(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            int d = digest[i];
            // byte型では128～255が負値になっているので補正
            if (d < 0) {
                d += 256;
            }
            // 0～15は16進数で1けたになるので、2けたになるよう頭に0を追加
            if (d < 16) {
                sb.append("0");
            }
            // ダイジェスト値の1バイトを16進数2けたで表示
            sb.append(Integer.toString(d, 16));
        }
        return sb.toString();
    }

    private static MessageDigest getDigest(final String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
