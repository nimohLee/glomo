package com.devleoh.glomo.util;

import org.apache.tika.utils.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.*;

/**
 * packageName    : com.devleoh.glomo.util
 * fileName       : EncryptUtilTest
 * author         : nimoh
 * date           : 2024/06/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/06/24        nimoh       최초 생성
 */
class SHA256Test {

    @Test
    void Salt_생성() {
        String salt = SHA256.createSalt();

        assertThat(salt).isNotEmpty();
        System.out.println(salt);
        assertThat(salt.length()).isEqualTo(40);
    }

    @Test
    void SHA256으로_해시생성() throws NoSuchAlgorithmException {
        String text = "abcd1234";
        String salt = SHA256.createSalt();
        String encryptedText = SHA256.encrypt(text, salt);

        assertThat(encryptedText).isNotEmpty();
        assertThat(encryptedText).isNotEqualTo(text);
    }

}
