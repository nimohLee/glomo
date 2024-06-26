package com.devleoh.glomo.member.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    public static final String USERNAME = "username";
    public static final String USER_ID = "userId";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    private Member user;

    @BeforeEach
    void setUp() {
        user = new Member(USERNAME, USER_ID, PASSWORD, EMAIL);
    }

    @Test
    void isSameUserName() {
        final String failName = "failName";

        assertAll(
                () -> assertThat(user.isSameName(USERNAME)).isTrue(),
                () -> assertThat(user.isSameName(failName)).isFalse()
        );
    }

    @Test
    void isSameUserId() {
        final String failId = "failId";

        assertAll(
                () -> assertThat(user.isSameMemberId(USER_ID)).isTrue(),
                () -> assertThat(user.isSameMemberId(failId)).isFalse()
        );
    }
}
