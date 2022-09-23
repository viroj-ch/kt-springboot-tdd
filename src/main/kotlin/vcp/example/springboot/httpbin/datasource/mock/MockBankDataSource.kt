package vcp.example.springboot.httpbin.datasource.mock

import org.springframework.stereotype.Repository
import vcp.example.springboot.httpbin.datasource.BankDataSource
import vcp.example.springboot.httpbin.model.Bank
import java.lang.IllegalArgumentException

@Repository
class MockBankDataSource : BankDataSource {
    val banks = mutableListOf<Bank>(
            Bank("1234", 0.0, 1),
            Bank("0002", 1.2, 120),
            Bank("0002", 1.2, 0),
    )

    override fun getBanks(): Collection<Bank> = banks
    override fun getBank(accountNumber: String) : Bank = banks.firstOrNull() { it.accountNumber == accountNumber}
            ?: throw NoSuchElementException("Could not find a bank with account number $accountNumber")

    override fun addBank(bank: Bank): Bank {
        if (banks.any {it.accountNumber == bank.accountNumber}) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exists.")
        }
        banks.add(bank)

        return bank
    }

    override fun update(bank: Bank): Bank {
        val currentBank = banks.first() { it.accountNumber == bank.accountNumber }

        banks.remove(currentBank)
        banks.add(bank)

        return bank
    }
}