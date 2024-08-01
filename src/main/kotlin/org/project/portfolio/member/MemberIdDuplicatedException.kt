package org.project.portfolio.member

class MemberIdDuplicatedException : RuntimeException(
    "중복되는 회원 아이디가 이미 존재합니다."
) {
}