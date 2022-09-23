package vcp.example.springboot.httpbin.datasource

import vcp.example.springboot.httpbin.model.Bank

interface BankDataSource {
    fun getBanks(): Collection<Bank>
    fun getBank(accountNumber: String) : Bank
}