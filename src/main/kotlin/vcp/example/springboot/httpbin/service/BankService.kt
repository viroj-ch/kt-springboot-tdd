package vcp.example.springboot.httpbin.service

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import vcp.example.springboot.httpbin.datasource.BankDataSource
import vcp.example.springboot.httpbin.model.Bank

@Service
class BankService(@Qualifier("mock data") private val dataSource: BankDataSource) {

    fun getBanks(): Collection<Bank> = dataSource.getBanks()
    fun getBank(accountNumber: String): Bank = dataSource.getBank(accountNumber)
    fun addBank(bank: Bank): Bank = dataSource.addBank(bank)
    fun updateBank(bank: Bank): Bank = dataSource.update(bank)
    fun deleteBank(accountNumber: String) {
        dataSource.deleteBank(accountNumber)
    }
}