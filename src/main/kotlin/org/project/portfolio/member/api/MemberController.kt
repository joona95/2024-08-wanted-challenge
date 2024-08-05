package org.project.portfolio.member.api

import org.project.portfolio.member.application.dto.MemberCreateRequest
import org.project.portfolio.member.application.dto.MemberLoginRequest
import org.project.portfolio.member.application.dto.MemberLoginResponse
import org.project.portfolio.member.application.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/members")
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping
    fun join(
        @RequestBody request: MemberCreateRequest
    ) {
        memberService.join(request)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: MemberLoginRequest
    ): MemberLoginResponse {
        return memberService.login(request)
    }
}