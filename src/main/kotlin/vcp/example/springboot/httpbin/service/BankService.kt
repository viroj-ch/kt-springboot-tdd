package vcp.example.springboot.httpbin.service

import org.springframework.stereotype.Service
import vcp.example.springboot.httpbin.datasource.BankDataSource
import vcp.example.springboot.httpbin.model.Bank

@Service
class BankService(private val dataSource: BankDataSource) {

    fun getBanks(): Collection<Bank> {
        return emptyList()
    }
}