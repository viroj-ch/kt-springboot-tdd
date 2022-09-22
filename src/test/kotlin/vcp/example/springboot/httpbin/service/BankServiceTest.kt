package vcp.example.springboot.httpbin.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import vcp.example.springboot.httpbin.datasource.BankDataSource

internal class BankServiceTest {

    private val dataSource: BankDataSource = mockk()
    private val bankService = BankService(dataSource)

    @Test
    fun `should call its data source to retrieve banks`() {
        //given
        every { dataSource.getBanks() } returns emptyList()
        
        //when
        val banks = bankService.getBanks()
        
        //then
        verify(exactly = 1) { dataSource.getBanks() }
    }
    
}