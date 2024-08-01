package org.project.portfolio.member

data class MemberCreateRequest(
    val memberId: String,
    val email: String,
    val password: String,
    val memberName: String,
    val phoneNumber: String
) {

    fun toEntity() : Member {
        return Member(
            memberId = this.memberId,
            email = this.email,
            password = this.password,
            memberName = this.memberName,
            phoneNumber = this.phoneNumber
        )
    }
}