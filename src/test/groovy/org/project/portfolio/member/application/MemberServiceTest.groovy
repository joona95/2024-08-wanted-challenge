package org.project.portfolio.member.application

import org.project.portfolio.member.application.MemberService
import org.project.portfolio.member.application.dto.MemberCreateRequest
import org.project.portfolio.member.exception.MemberIdDuplicatedException
import org.project.portfolio.member.infra.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

class MemberServiceTest extends Specification {

    private MemberRepository memberRepository = Mock()
    private PasswordEncoder passwordEncoder = Mock()
    private MemberService memberService = new MemberService(memberRepository, passwordEncoder)

    def "회원가입 시 유효성 검증"() {

        given:
        MemberCreateRequest request = new MemberCreateRequest(
                memberId,
                email,
                password,
                memberName,
                phoneNumber
        )
        passwordEncoder.encode(password) >> password
        memberRepository.existsByMemberId(memberId) >> false

        when:
        memberService.join(request)

        then:
        def e = thrown(IllegalArgumentException.class)
        e.message == message

        where:
        memberId   | email            | password    | memberName | phoneNumber      || message
        ""         | "test@gmail.com" | "abcd123!!" | "가나다"      | "000-0000-0000"  || "아이디 형식이 올바르지 않습니다. (1글자 이상 대소문자)"
        "1234"     | "test@gmail.com" | "abcd123!!" | "가나다"      | "000-0000-0000"  || "아이디 형식이 올바르지 않습니다. (1글자 이상 대소문자)"
        "test1234" | "test@gmail.com" | "abcd123!!" | "가나다"      | "000-0000-0000"  || "아이디 형식이 올바르지 않습니다. (1글자 이상 대소문자)"
        "TEST1234" | "test@gmail.com" | "abcd123!!" | "가나다"      | "000-0000-0000"  || "아이디 형식이 올바르지 않습니다. (1글자 이상 대소문자)"
        "가나다"      | "test@gmail.com" | "abcd123!!" | "가나다"      | "000-0000-0000"  || "아이디 형식이 올바르지 않습니다. (1글자 이상 대소문자)"
        "test"     | ""               | "abcd123!!" | "가나다"      | "000-0000-0000"  || "이메일 형식이 올바르지 않습니다. (ex. ooo@oooo.ooo)"
        "test"     | "test@gmail"     | "abcd123!!" | "가나다"      | "000-0000-0000"  || "이메일 형식이 올바르지 않습니다. (ex. ooo@oooo.ooo)"
        "test"     | "test*gmail.com" | "abcd123!!" | "가나다"      | "000-0000-0000"  || "이메일 형식이 올바르지 않습니다. (ex. ooo@oooo.ooo)"
        "test"     | "test*gmail.com" | "abcd123!!" | "가나다"      | "000-0000-0000"  || "이메일 형식이 올바르지 않습니다. (ex. ooo@oooo.ooo)"
        "test"     | "test@gmail.com" | ""          | "가나다"      | "000-0000-0000"  || "비밀번호 형식이 올바르지 않습니다. (대소문자와 숫자 5글자 이상, 특수문자 2글자 이상 포함)"
        "test"     | "test@gmail.com" | "abcd!!"    | "가나다"      | "000-0000-0000"  || "비밀번호 형식이 올바르지 않습니다. (대소문자와 숫자 5글자 이상, 특수문자 2글자 이상 포함)"
        "test"     | "test@gmail.com" | "1234!!"    | "가나다"      | "000-0000-0000"  || "비밀번호 형식이 올바르지 않습니다. (대소문자와 숫자 5글자 이상, 특수문자 2글자 이상 포함)"
        "test"     | "test@gmail.com" | "ab12!!"    | "가나다"      | "000-0000-0000"  || "비밀번호 형식이 올바르지 않습니다. (대소문자와 숫자 5글자 이상, 특수문자 2글자 이상 포함)"
        "test"     | "test@gmail.com" | "abcd123!"  | "가나다"      | "000-0000-0000"  || "비밀번호 형식이 올바르지 않습니다. (대소문자와 숫자 5글자 이상, 특수문자 2글자 이상 포함)"
        "test"     | "test@gmail.com" | "abcd123"   | "가나다"      | "000-0000-0000"  || "비밀번호 형식이 올바르지 않습니다. (대소문자와 숫자 5글자 이상, 특수문자 2글자 이상 포함)"
        "test"     | "test@gmail.com" | "abcd123!!" | ""         | "000-0000-0000"  || "이름 형식이 올바르지 않습니다. (1글자 이상 한글)"
        "test"     | "test@gmail.com" | "abcd123!!" | "abc"      | "000-0000-0000"  || "이름 형식이 올바르지 않습니다. (1글자 이상 한글)"
        "test"     | "test@gmail.com" | "abcd123!!" | "DEF"      | "000-0000-0000"  || "이름 형식이 올바르지 않습니다. (1글자 이상 한글)"
        "test"     | "test@gmail.com" | "abcd123!!" | "000"      | "000-0000-0000"  || "이름 형식이 올바르지 않습니다. (1글자 이상 한글)"
        "test"     | "test@gmail.com" | "abcd123!!" | "가나다abc"   | "000-0000-0000"  || "이름 형식이 올바르지 않습니다. (1글자 이상 한글)"
        "test"     | "test@gmail.com" | "abcd123!!" | "가나다"      | ""               || "휴대폰 번호 형식이 올바르지 않습니다. (ex. 000-0000-0000)"
        "test"     | "test@gmail.com" | "abcd123!!" | "가나다"      | "00000000000"    || "휴대폰 번호 형식이 올바르지 않습니다. (ex. 000-0000-0000)"
        "test"     | "test@gmail.com" | "abcd123!!" | "가나다"      | "000-00000000"   || "휴대폰 번호 형식이 올바르지 않습니다. (ex. 000-0000-0000)"
        "test"     | "test@gmail.com" | "abcd123!!" | "가나다"      | "0000-0000-0000" || "휴대폰 번호 형식이 올바르지 않습니다. (ex. 000-0000-0000)"
    }

    def "회원가입 시 중복 아이디 여부 확인"() {

        given:
        var memberId = "test"
        var email = "test@gmail.com"
        var password = "abcd123!!"
        var memberName = "가나다"
        var phoneNumber = "000-0000-0000"
        MemberCreateRequest request = new MemberCreateRequest(
                memberId,
                email,
                password,
                memberName,
                phoneNumber
        )
        passwordEncoder.encode(password) >> password
        memberRepository.existsByMemberId(memberId) >> true

        when:
        memberService.join(request)

        then:
        def e = thrown(MemberIdDuplicatedException.class)
        e.message == "중복되는 회원 아이디가 이미 존재합니다."

    }
}
