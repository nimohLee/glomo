package com.devleoh.glomo.member.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.devleoh.glomo.member.service
 * fileName       : MemberExceptionMessage
 * author         : nimoh
 * date           : 2024/06/24
 * description    : MemberException에 포함될 메시지 Enum
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/06/24        nimoh       최초 생성
 */
@Getter
@RequiredArgsConstructor
public enum MemberExceptionMessage {

    DUPLICATE_MEMBER_ID("Duplicate Member Id")
    , DUPLICATE_EMAIL("Duplicate Email")
    , MEMBER_NOT_FOUND("Member Not Found");
    private final String message;
}
