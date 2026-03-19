package com.satyamthakur.gatekeeper.rules

import com.satyamthakur.gatekeeper.model.UserContext
import com.satyamthakur.gatekeeper.rules.Rule

class CountryRule(private val value: String) : Rule {

    override fun matches(user: UserContext): Boolean {
        return user.country == value
    }
}