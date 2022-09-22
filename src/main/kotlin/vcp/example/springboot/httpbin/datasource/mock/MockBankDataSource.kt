package vcp.example.springboot.httpbin.datasource.mock

import org.springframework.stereotype.Repository
import vcp.example.springboot.httpbin.datasource.BankDataSource
import vcp.example.springboot.httpbin.model.Bank

@Repository
class MockBankDataSource :BankDataSource {
    override fun getBanks(): Collection<Bank> {
        TODO("Not yet implemented")
    }
}