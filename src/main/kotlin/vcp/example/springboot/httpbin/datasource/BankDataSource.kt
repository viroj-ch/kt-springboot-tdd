package vcp.example.springboot.httpbin.datasource

import vcp.example.springboot.httpbin.model.Bank

interface BankDataSource {
    fun getBanks(): Collection<Bank>
    fun getBank(accountNumber: String) : Bank
    fun addBank(bank: Bank): Bank
    fun update(bank: Bank): Bank
    fun deleteBank(accountNumber: String)
}