package org.project.portfolio

import java.util.regex.Pattern

fun isValidEmail(email: String): Boolean {

    val emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    val pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(email)

    return matcher.matches()
}

fun isValidPhoneNumber(phoneNumber: String): Boolean {

    val phoneNumberRegex = "^\\d{3}-\\d{3,4}-\\d{4}$"
    val pattern = Pattern.compile(phoneNumberRegex, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(phoneNumber)

    return matcher.matches()
}

fun isValidMemberId(memberId: String): Boolean {

    val memberIdRegex = "^[a-zA-Z]+$"
    val pattern = Pattern.compile(memberIdRegex, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(memberId)

    return matcher.matches()
}

fun isValidMemberName(memberName: String): Boolean {

    val memberNameRegex = "^[가-힣]+$"
    val pattern = Pattern.compile(memberNameRegex, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(memberName)

    return matcher.matches()
}

fun isValidPassword(password: String): Boolean {

    val passwordRegex = "^(?=.*?[a-zA-Z0-9]{5,})(?=.*?[#?!@\$ %^&*-]{2,}).{1,}$"
    val pattern = Pattern.compile(passwordRegex, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(password)

    return matcher.matches()
}