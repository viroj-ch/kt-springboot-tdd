package vcp.example.springboot.httpbin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vcp.example.springboot.httpbin.model.Bank
import vcp.example.springboot.httpbin.service.BankService

@RestController
@RequestMapping("/api/banks")
class BankController(private val bankService: BankService) {

    @GetMapping
    fun getBanks() : Collection<Bank> = bankService.getBanks()
}