package com.devleoh.glomo.member.repository;

import com.devleoh.glomo.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.devleoh.glomo.user.repository
 * fileName       : UserRepository
 * author         : nimoh
 * date           : 2024/06/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/06/17        nimoh       최초 생성
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberId(String memberId);

    boolean existsByMemberId(String memberId);

    boolean existsByEmail(String email);
}
