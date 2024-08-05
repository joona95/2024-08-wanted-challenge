package org.project.portfolio.config

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.project.portfolio.common.JwtProvider
import org.project.portfolio.member.MemberDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean

@Component
class JwtFilter(
    private val jwtProvider: JwtProvider,
    private val memberDetailsService: MemberDetailsService
): GenericFilterBean() {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        val httpServletRequest = request as HttpServletRequest
        val token = jwtProvider.resolveToken(httpServletRequest)
        val requestURI = httpServletRequest.requestURI

        if (!token.isNullOrBlank() && jwtProvider.isValidToken(token)) {
            val userId = jwtProvider.getId(token)
            val userDetails = memberDetailsService.loadUserByUsername(userId.toString())
            val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            SecurityContextHolder.getContext().authentication = authentication
        }

        chain?.doFilter(request, response)
    }
}