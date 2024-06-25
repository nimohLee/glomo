package com.devleoh.glomo.member.exception;

/**
 * packageName    : com.devleoh.glomo.member.service
 * fileName       : MemberException
 * author         : nimoh
 * date           : 2024/06/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/06/24        nimoh       최초 생성
 */
public class MemberException extends RuntimeException {
    public MemberException(MemberExceptionMessage memberExceptionMessage) {
        super(memberExceptionMessage.getMessage());
    }
}
