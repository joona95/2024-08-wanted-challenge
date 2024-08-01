package org.project.portfolio.member

import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun createMember(request: MemberCreateRequest) {
        memberRepository.save(request.toEntity())
    }
}