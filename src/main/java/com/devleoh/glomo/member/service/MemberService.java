package com.devleoh.glomo.member.service;

import com.devleoh.glomo.member.domain.Member;
import com.devleoh.glomo.member.exception.MemberException;
import com.devleoh.glomo.member.exception.MemberExceptionMessage;
import com.devleoh.glomo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

/**
 * packageName    : com.devleoh.glomo.member.service
 * fileName       : MemberService
 * author         : nimoh
 * date           : 2024/06/24
 * description    : Member 관련 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/06/24        nimoh       최초 생성
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 멤버 생성
     * @param member 생성(저장)할 Member Entity
     * @return 저장된 Member의 ID
     * @throws MemberException MemberId 혹은 Email이 이미 존재하는 경우 예외 발생
     */
    @Transactional
    public long createMember(final Member member) throws NoSuchAlgorithmException {
        if (memberRepository.existsByMemberId(member.getMemberId())) {
            throw new MemberException(MemberExceptionMessage.DUPLICATE_MEMBER_ID);
        }

        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new MemberException(MemberExceptionMessage.DUPLICATE_EMAIL);
        }

        member.encryptPassword();
        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }

    /**
     * Member의 ID를 사용하여 Member 단 건 조회
     *
     * @param id 조회할 Member의 ID
     * @return 조회된 Member
     * @throws MemberException 해당하는 Member가 없는 경우 예외 발생
     */
    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new MemberException(MemberExceptionMessage.MEMBER_NOT_FOUND));
    }
}
