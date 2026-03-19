package com.satyamthakur.gatekeeper.controller

import com.satyamthakur.gatekeeper.model.FlagRule
import com.satyamthakur.gatekeeper.repository.FlagRuleRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rules")
class RuleController(
    private val repository: FlagRuleRepository
) {

    @PostMapping
    fun createRule(
        @RequestParam flagName: String,
        @RequestParam attribute: String,
        @RequestParam operator: String,
        @RequestParam value: String
    ): FlagRule {

        val rule = FlagRule(
            flagName = flagName,
            attribute = attribute,
            operator = operator,
            value = value
        )

        return repository.save(rule)
    }
}