package com.satyamthakur.gatekeeper.rules

import com.satyamthakur.gatekeeper.model.FlagRule
import org.springframework.stereotype.Component

@Component
class RuleFactory {

    fun create(rule: FlagRule): Rule {

        return when (rule.attribute) {

            "country" -> CountryRule(rule.value)

            "device" -> DeviceRule(rule.value)

            "email" -> EmailRule(rule.value)

            else -> throw RuntimeException("Unknown rule")
        }
    }
}