package com.devleoh.glomo.member.domain;

import com.devleoh.glomo.base.BaseEntity;
import com.devleoh.glomo.util.SHA256;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.NoSuchAlgorithmException;

/**
 * packageName    : com.devleoh.glomo.user.domain
 * fileName       : User
 * author         : nimoh
 * date           : 2024/06/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/06/17        nimoh       최초 생성
 */
@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String memberId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String passwordSalt = "";

    @Column(nullable = false)
    private String email;

    public Member(String name, String memberId, String password, String email) {
        this.name = name;
        this.memberId = memberId;
        this.password = password;
        this.email = email;
    }

    public boolean isSameName(final String name) {
        return this.name.equals(name);
    }

    public boolean isSameMemberId(final String memberId) {
        return this.memberId.equals(memberId);
    }

    public void changeName(final String name) {
        this.name = name;
    }

    public void changeMemberId(final String memberId) {
        this.memberId = memberId;
    }

    public void encryptPassword() throws NoSuchAlgorithmException {
        String salt = SHA256.createSalt();
        password = SHA256.encrypt(password, salt);
        passwordSalt = salt;
    }
}
