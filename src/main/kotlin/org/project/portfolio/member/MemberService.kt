package org.project.portfolio.member

import com.google.common.base.Preconditions
import org.project.portfolio.*
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun createMember(request: MemberCreateRequest) {

        checkValidMemberCreateRequest(request)
        checkDuplicatedMemberId(request.memberId)

        memberRepository.save(request.toEntity(passwordEncoder))
    }

    private fun checkValidMemberCreateRequest(request: MemberCreateRequest) {

        Preconditions.checkArgument(isValidEmail(request.email), "이메일 형식이 올바르지 않습니다. (ex. ooo@oooo.ooo)")
        Preconditions.checkArgument(isValidPhoneNumber(request.phoneNumber), "휴대폰 번호 형식이 올바르지 않습니다. (ex. 000-0000-0000)")
        Preconditions.checkArgument(isValidMemberId(request.memberId), "아이디 형식이 올바르지 않습니다. (1글자 이상 대소문자)")
        Preconditions.checkArgument(isValidMemberName(request.memberName), "이름 형식이 올바르지 않습니다. (1글자 이상 한글)")
        Preconditions.checkArgument(
            isValidPassword(request.password),
            "비밀번호 형식이 올바르지 않습니다. (대소문자와 숫자 5글자 이상, 특수문자 2글자 이상 포함)"
        )
    }

    private fun checkDuplicatedMemberId(memberId: String) {

        if (memberRepository.existsByMemberId(memberId)) {
            throw MemberIdDuplicatedException()
        }
    }
}