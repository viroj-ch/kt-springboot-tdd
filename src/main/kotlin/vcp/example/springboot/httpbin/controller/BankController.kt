package vcp.example.springboot.httpbin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import vcp.example.springboot.httpbin.model.Bank

@RestController
@RequestMapping("/api/banks")
class BankController {

    @GetMapping
    fun getBanks() : Collection<Bank> = emptyList()
}