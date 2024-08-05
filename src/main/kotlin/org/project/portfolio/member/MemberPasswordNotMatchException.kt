package org.project.portfolio.member

import java.lang.RuntimeException

class MemberPasswordNotMatchException : RuntimeException(
    "회원 비밀번호가 일치하지 않습니다."
) {
}