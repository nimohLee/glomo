package com.devleoh.glomo.member.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * packageName    : com.devleoh.glomo.user.domain
 * fileName       : UserTest
 * author         : nimoh
 * date           : 2024/06/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/06/17        nimoh       최초 생성
 */
public class MemberTest {

    public static final String NAME = "username";
    public static final String MEMBER_ID = "userId";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member(NAME, MEMBER_ID, PASSWORD, EMAIL);
    }

    @Test
    void isSameUserName() {
        final String failName = "failName";

        assertAll(
                () -> assertThat(member.isSameName(NAME)).isTrue(),
                () -> assertThat(member.isSameName(failName)).isFalse()
        );
    }

    @Test
    void isSameUserId() {
        final String failId = "failId";

        assertAll(
                () -> assertThat(member.isSameMemberId(MEMBER_ID)).isTrue(),
                () -> assertThat(member.isSameMemberId(failId)).isFalse()
        );
    }

    @Test
    void encryptPassword() throws NoSuchAlgorithmException {
        String originalPassword = member.getPassword();
        member.encryptPassword();
        String encryptedPassword = member.getPassword();
        assertThat(originalPassword).isNotEqualTo(encryptedPassword);
    }
}
