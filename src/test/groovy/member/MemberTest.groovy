package member

import org.project.portfolio.member.Member
import spock.lang.Specification

class MemberTest extends Specification {

    void "회원 생성 성공"() {

        when:
        var member = new Member(
                memberId,
                email,
                password,
                memberName,
                phoneNumber
        )

        then:
        member.memberId == memberId
        member.email == email
        member.password == password
        member.memberName == memberName
        member.phoneNumber == phoneNumber

        where:
        memberId | email            | password    | memberName | phoneNumber
        "test"   | "test@gmail.com" | "abcd123!!" | "가나다"      | "000-0000-0000"
        "TEST"   | "test@gmail.com" | "abcd123!!" | "가나다"      | "000-0000-0000"
        "teST"   | "test@gmail.com" | "abcd123!!" | "가나다"      | "000-0000-0000"
        "test"   | "test@gmail.com" | "abcde!!"   | "가나다"      | "000-0000-0000"
        "test"   | "test@gmail.com" | "12345!!"   | "가나다"      | "000-0000-0000"
        "test"   | "test@gmail.com" | "abc12!!"   | "가나다"      | "000-0000-0000"
        "test"   | "test@gmail.com" | "abcd123!!" | "가나"       | "000-0000-0000"
        "test"   | "test@gmail.com" | "abcd123!!" | "가나다"      | "000-000-0000"
    }

    void "회원 생성 시 유효성 검증"() {

        when:
        new Member(
                memberId,
                email,
                password,
                memberName,
                phoneNumber
        )

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
}
