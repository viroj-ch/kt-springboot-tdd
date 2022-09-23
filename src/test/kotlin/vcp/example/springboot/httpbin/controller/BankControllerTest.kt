package vcp.example.springboot.httpbin.controller

import com.fasterxml.jackson.databind.ObjectMapper
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
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post
import vcp.example.springboot.httpbin.model.Bank


@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
        val mockMvc: MockMvc,
        val objectMapper: ObjectMapper,
) {

    private val baseUrl = "/api/banks"

    @Nested
    @DisplayName("GET /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should return all banks`() {
            //when
            //then
            mockMvc.get(baseUrl)
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
            mockMvc.get("$baseUrl/$accountNumber")
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
            mockMvc.get("$baseUrl/$accountNumber")
                    .andDo { print() }
                    .andExpect {
                        status { isNotFound() }
                        content { string("Could not find a bank with account number $accountNumber") }
                    }
        }
    }

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class AddNewBank {

        @Test
        fun `should add the new bank`() {
            //given
            val newBank = Bank("00005", 150.0001, 35)
            
            //when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            //then
            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.accountNumber") { value("00005")}
                    jsonPath("$.trust") { value(150.0001)}
                    jsonPath("$.transactionFee") { value(35)}
                }
        }
        
        @Test
        fun `should return BAD REQUEST if given bank already exists`() {
            //given
            val newBank = Bank("1234", 150.0001, 35)

            //when
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            //then
            performPost
                    .andDo { print() }
                    .andExpect {
                        status { isBadRequest() }
                        content { string("Bank with account number ${newBank.accountNumber} already exists.") }
                    }
        }
    }

    @Nested
    @DisplayName("PATCH /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class UpdateBank {

        @Test
        fun `should update an existing bank`() {
            //given
            val updateBank = Bank("1234", 150.0001, 35)

            //when
            val performPatchRequest = mockMvc.patch(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updateBank)
            }

            //then
            performPatchRequest
                    .andDo { print() }
                    .andExpect {
                        status { isOk() }
                    }
        }
    }
}
