package vcp.example.springboot.httpbin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HttpbinApplication

fun main(args: Array<String>) {
	runApplication<HttpbinApplication>(*args)
}
