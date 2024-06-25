package com.devleoh.glomo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * packageName    : com.devleoh.glomo.util
 * fileName       : EncryptUtil
 * author         : nimoh
 * date           : 2024/06/24
 * description    : SHA256 단방향 암호화를 관리하는 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/06/24        nimoh       최초 생성
 */
public class SHA256 {

    public static final String SHA_256 = "SHA-256";
    public static final String BYTES_TO_HEX_FORMAT = "%02x";

    /**
     * SHA256 암호화에 사용할 40자 Salt Key 생성
     *
     * @return Salt key
     */
    public static String createSalt() {
        SecureRandom  r = new SecureRandom();
        byte[] salt = new byte[20];

        r.nextBytes(salt);

        return bytesToHex(salt);
    }

    /**
     * SHA256와 Salt로 문자열을 암호화하고, 그 결과를 반환
     *
     * @param text 암호화할 텍스트
     * @param salt SHA256에 사용될 Salt 문자열
     * @return 암호화된 텍스트
     * @throws NoSuchAlgorithmException
     */
    public static String encrypt(String text, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(SHA_256);
        md.update((text + salt).getBytes());

        return bytesToHex(md.digest());
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format(BYTES_TO_HEX_FORMAT, b));
        }
        return builder.toString();
    }
}
