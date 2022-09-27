package vcp.example.springboot.httpbin.model

import javax.validation.constraints.NotBlank

data class Bank(
        @field: NotBlank
        val accountNumber: String,
        val trust: Double,
        val transactionFee: Int,
)