package org.project.portfolio

import com.google.common.base.Preconditions

class Member(
    private var memberId: String,
    private var email: String,
    private var password: String,
    private var memberName: String,
    private var phoneNumber: String
) {

    init {
        Preconditions.checkArgument(isValidEmail(this.email), "이메일 형식이 올바르지 않습니다.")
        Preconditions.checkArgument(isValidPhoneNumber(this.phoneNumber), "휴대폰 번호 형식이 올바르지 않습니다. (ex. 000-0000-0000)")
        Preconditions.checkArgument(isValidMemberId(this.memberId), "아이디는 대소문자 형식이어야 합니다.")
        Preconditions.checkArgument(isValidMemberName(this.memberName), "이름은 한글 형식이어야 합니다.")
        Preconditions.checkArgument(isValidPassword(this.password), "비밀번호는 대소문자와 숫자 5글자 이상, 특수문자 2글자 이상 포함하여야 합니다.")
    }
}