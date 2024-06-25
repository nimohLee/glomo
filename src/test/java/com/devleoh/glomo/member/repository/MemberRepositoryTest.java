package com.devleoh.glomo.member.repository;

import com.devleoh.glomo.member.domain.Member;
import com.devleoh.glomo.util.SHA256;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

/**
 * packageName    : com.devleoh.glomo.user.repository
 * fileName       : UserRepositoryTest
 * author         : nimoh
 * date           : 2024/06/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/06/17        nimoh       최초 생성
 */

@DataJpaTest
class MemberRepositoryTest {

    public static final String MEMBER_NAME = "memberName";
    public static final String MEMBER_ID = "memberId";

    @Autowired
    private MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager em;

    private Member member;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException {
        String salt = SHA256.createSalt();
        member = new Member(MEMBER_NAME, MEMBER_ID, "password", "email");
    }

    @Test
    void 회원가입() throws Exception {
        // when
        final Member result = memberRepository.save(member);
        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.isSameName(MEMBER_NAME)).isTrue();
        assertThat(result.isSameMemberId(MEMBER_ID)).isTrue();
    }

    @Test
    void 멤버ID로_회원조회() {
        //when
        final Member savedMember = memberRepository.save(member);
        em.flush();
        final Member result = memberRepository.findByMemberId(savedMember.getMemberId());
        //then
        assertThat(result.getId()).isEqualTo(savedMember.getId());
    }

    @Test
    void 멤버ID로_회원존재유무확인() {
        //when
        final Member savedMember = memberRepository.save(member);
        em.flush();
        final boolean result = memberRepository.existsByMemberId(savedMember.getMemberId());
        final boolean wrongResult = memberRepository.existsByMemberId("WrongMemberId");
        //then
        assertThat(result).isTrue();
        assertThat(wrongResult).isFalse();
    }

    @Test
    void 이메일로_회원존재유무확인() {
        //when
        final Member savedMember = memberRepository.save(member);
        em.flush();
        final boolean result = memberRepository.existsByEmail(savedMember.getEmail());
        final boolean wrongResult = memberRepository.existsByEmail("WrongEmail");
        //then
        assertThat(result).isTrue();
        assertThat(wrongResult).isFalse();
    }

    @Test
    void 회원수정() throws Exception {
        // given
        final Member savedMember = memberRepository.save(member);
        em.flush();
        // when
        Member findMember = memberRepository.findById(savedMember.getId()).get();
        String changedName = "userName2";
        String changedMemberId = "userId2";

        findMember.changeName(changedName);
        findMember.changeMemberId(changedMemberId);

        em.flush();

        Member updatedMember = memberRepository.findById(savedMember.getId()).get();
        // then
        assertThat(updatedMember.getId()).isEqualTo(findMember.getId());
        assertThat(updatedMember.isSameMemberId(changedMemberId)).isTrue();
        assertThat(updatedMember.isSameName(changedName)).isTrue();
    }

    @Test
    void 회원삭제() throws Exception {
        // given
        final Member savedMember = memberRepository.save(member);
        em.flush();
        // when
        memberRepository.delete(savedMember);
        em.flush();

        Optional<Member> findMember = memberRepository.findById(savedMember.getId());
        // then
        assertThat(findMember).isEmpty();
    }

}
