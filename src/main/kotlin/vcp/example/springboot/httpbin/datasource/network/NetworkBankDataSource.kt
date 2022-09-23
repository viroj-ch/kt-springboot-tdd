package vcp.example.springboot.httpbin.datasource.network

import org.springframework.stereotype.Repository
import vcp.example.springboot.httpbin.datasource.BankDataSource
import vcp.example.springboot.httpbin.model.Bank

@Repository("network")
class NetworkBankDataSource : BankDataSource {
    override fun getBanks(): Collection<Bank> {
        TODO("Not yet implemented")
    }

    override fun getBank(accountNumber: String): Bank {
        TODO("Not yet implemented")
    }

    override fun addBank(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun update(bank: Bank): Bank {
        TODO("Not yet implemented")
    }

    override fun deleteBank(accountNumber: String) {
        TODO("Not yet implemented")
    }

}