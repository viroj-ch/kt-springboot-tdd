package vcp.example.springboot.httpbin.datasource.mock

import org.springframework.stereotype.Repository
import vcp.example.springboot.httpbin.datasource.BankDataSource
import vcp.example.springboot.httpbin.model.Bank

@Repository
class MockBankDataSource : BankDataSource {
    val banks = listOf(
            Bank("1234", 0.0, 1),
            Bank("0002", 1.2, 120),
            Bank("0002", 1.2, 0),
    )

    override fun getBanks(): Collection<Bank> = banks
}