package org.project.portfolio.member.exception

import java.lang.RuntimeException

class MemberNotFoundException: RuntimeException(
    "해당하는 회원을 찾을 수 없습니다."
) {
}