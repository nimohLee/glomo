package com.devleoh.glomo.member.service;

import com.devleoh.glomo.member.domain.Member;
import com.devleoh.glomo.member.exception.MemberException;
import com.devleoh.glomo.member.repository.MemberRepository;
import com.devleoh.glomo.util.SHA256;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

import static com.devleoh.glomo.member.exception.MemberExceptionMessage.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * packageName    : com.devleoh.glomo.member.service
 * fileName       : MemberServiceTest
 * author         : nimoh
 * date           : 2024/06/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/06/24        nimoh       최초 생성
 */
@SpringBootTest
@Transactional
public class MemberServiceTest {

    public static final String MEMBER_PASSWORD = "1234";
    @Autowired
    private MemberService memberService;

    @SpyBean
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException {
        member = new Member("test","test", MEMBER_PASSWORD, "test@test.com");
    }
    @Nested
    class 멤버조회 {
        @Test
        void 실패_ID에_해당하는_멤버없음_() {
            //given
            //when
            //then
            assertThatThrownBy(() -> memberService.findById(1L)).isInstanceOf(MemberException.class).hasMessageContaining(MEMBER_NOT_FOUND.getMessage());
        }

        @Test
        void 성공() throws NoSuchAlgorithmException {
            //given
            memberService.createMember(member);
            //when
            Member findMember = memberService.findById(member.getId());
            //then
            assertThat(findMember.getId()).isEqualTo(member.getId());
            assertThat(findMember.getMemberId()).isEqualTo(member.getMemberId());
            assertThat(findMember.getPassword()).isEqualTo(member.getPassword());
            assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
        }
    }

    @Nested
    class 회원가입 {
        @Test
        void 실패_이미_존재하는_아이디() {
            //given
            when(memberRepository.existsByMemberId(member.getMemberId())).thenReturn(true);

            Member newMember = new Member("ace","test","1234", "ace@ace.com");
            //when
            //then
            assertThatThrownBy(() -> memberService.createMember(newMember)).isInstanceOf(MemberException.class).hasMessageContaining(DUPLICATE_MEMBER_ID.getMessage());
        }

        @Test
        void 실패_이미_존재하는_이메일() {
            //given
            when(memberRepository.existsByEmail(member.getEmail())).thenReturn(true);

            Member newMember = new Member("ace","ace","1234", "test@test.com");
            //when
            //then
            assertThatThrownBy(() -> memberService.createMember(newMember)).isInstanceOf(MemberException.class).hasMessageContaining(DUPLICATE_EMAIL.getMessage());
        }

        @Test
        void 성공() throws NoSuchAlgorithmException {
            //given
            //when
            long savedMemberId = memberService.createMember(member);
            Member findMember = memberService.findById(savedMemberId);
            //then
            assertThat(findMember).isEqualTo(member);
            assertThat(findMember.getPassword()).isEqualTo(SHA256.encrypt(MEMBER_PASSWORD, findMember.getPasswordSalt()));
        }
    }

}
