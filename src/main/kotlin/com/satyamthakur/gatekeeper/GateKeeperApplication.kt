package com.satyamthakur.gatekeeper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GateKeeperApplication

fun main(args: Array<String>) {
	runApplication<GateKeeperApplication>(*args)
}
