package org.project.portfolio.member

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface MemberRepository : JpaRepository<Member, Long> {

    fun existsByMemberId(memberId: String): Boolean

    fun findByMemberId(memberId: String): Optional<Member>
}