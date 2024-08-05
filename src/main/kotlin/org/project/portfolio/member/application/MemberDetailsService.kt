package org.project.portfolio.member.application

import org.project.portfolio.member.exception.MemberNotFoundException
import org.project.portfolio.member.infra.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MemberDetailsService(
    private val memberRepository: MemberRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return memberRepository.findById(username.toLong()).orElseThrow {
            throw MemberNotFoundException()
        }
    }
}