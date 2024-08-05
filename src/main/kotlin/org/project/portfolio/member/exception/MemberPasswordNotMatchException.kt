package org.project.portfolio.member.exception

import java.lang.RuntimeException

class MemberPasswordNotMatchException : RuntimeException(
    "회원 비밀번호가 일치하지 않습니다."
) {
}