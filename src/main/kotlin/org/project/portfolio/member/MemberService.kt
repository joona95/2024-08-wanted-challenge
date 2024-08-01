package org.project.portfolio.member

import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun createMember(request: MemberCreateRequest) {

        checkDuplicatedMemberId(request.memberId)

        memberRepository.save(request.toEntity())
    }

    private fun checkDuplicatedMemberId(memberId: String) {
        memberRepository.findByMemberId(memberId) ?: throw MemberIdDuplicatedException()
    }
}