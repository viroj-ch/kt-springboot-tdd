package vcp.example.springboot.httpbin.model

data class Bank(
        val accountNumber: String,
        val trust: Double,
        val transactionFee: Int,
)