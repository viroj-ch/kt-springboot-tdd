package vcp.example.springboot.httpbin.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest {

    private val mockDataSource = MockBankDataSource()

    @Test
    fun `should return a collection of banks`() {
        //given
        
        
        //when
        val banks = mockDataSource.getBanks()
        
        //then
        assertThat(banks).isNotEmpty
    }

}