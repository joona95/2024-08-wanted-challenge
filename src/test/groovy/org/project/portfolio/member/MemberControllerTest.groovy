package org.project.portfolio.member

import com.fasterxml.jackson.databind.ObjectMapper
import org.project.portfolio.config.SecurityConfig
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import spock.lang.Specification

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(MemberController.class)
@Import(SecurityConfig.class)
@MockBean(JpaMetamodelMappingContext.class)
class MemberControllerTest extends Specification {

    @Autowired
    private MockMvc mockMvc
    @Autowired
    private ObjectMapper objectMapper
    @SpringBean
    private MemberService memberService = Mock()

    def "회원가입 성공"() {

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

        expect:
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/members")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
    }
}
