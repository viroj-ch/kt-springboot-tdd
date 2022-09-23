package vcp.example.springboot.httpbin.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get


@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc
    
    @Nested
    @DisplayName("GET /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should return all banks`() {
            //when
            //then
            mockMvc.get("/api/banks")
                    .andDo { print() }
                    .andExpect {
                        status { isOk() }
                        content { contentType(MediaType.APPLICATION_JSON) }
                        jsonPath("$[0].accountNumber") { value("1234")}
                    }
        }
    }

    @Nested
    @DisplayName("GET /api/banks/{accountNumber}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBank {
        @Test
        fun `should return the bank with given account number`() {
            //given
            val accountNumber = "1234"

            //when
            //then
            mockMvc.get("/api/banks/$accountNumber")
                    .andDo { print() }
                    .andExpect {
                        status { isOk() }
                        content { contentType(MediaType.APPLICATION_JSON) }
                        jsonPath("$.accountNumber") { value("1234")}
                        jsonPath("$.trust") { value(0.0)}
                        jsonPath("$.transactionFee") { value(1)}
                    }
        }
        
        @Test
        fun `should return NOT FOUND if account number does not exist`() {
            //given
            val accountNumber = "does_not_xist"
            
            //when
            //then
            mockMvc.get("/api/banks/$accountNumber")
                    .andDo { print() }
                    .andExpect {
                        status { isNotFound() }
                        content { string("Could not find a bank with account number $accountNumber") }
                    }
        }
    }
}
