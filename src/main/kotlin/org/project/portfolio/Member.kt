package org.project.portfolio

import com.google.common.base.Preconditions
import jakarta.persistence.*
import org.project.portfolio.common.BaseEntity

@Entity
@Table(name = "member")
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    val id: Long,
    @Column(name = "member_id", nullable = false)
    var memberId: String,
    @Column(name = "email", nullable = false)
    var email: String,
    @Column(name = "password", nullable = false)
    var password: String,
    @Column(name = "member_name", nullable = false)
    var memberName: String,
    @Column(name = "phone_number", nullable = false)
    var phoneNumber: String
) : BaseEntity() {

    init {
        Preconditions.checkArgument(isValidEmail(this.email), "이메일 형식이 올바르지 않습니다. (ex. ooo@oooo.ooo)")
        Preconditions.checkArgument(isValidPhoneNumber(this.phoneNumber), "휴대폰 번호 형식이 올바르지 않습니다. (ex. 000-0000-0000)")
        Preconditions.checkArgument(isValidMemberId(this.memberId), "아이디 형식이 올바르지 않습니다. (1글자 이상 대소문자)")
        Preconditions.checkArgument(isValidMemberName(this.memberName), "이름 형식이 올바르지 않습니다. (1글자 이상 한글)")
        Preconditions.checkArgument(
            isValidPassword(this.password),
            "비밀번호 형식이 올바르지 않습니다. (대소문자와 숫자 5글자 이상, 특수문자 2글자 이상 포함)"
        )
    }
}