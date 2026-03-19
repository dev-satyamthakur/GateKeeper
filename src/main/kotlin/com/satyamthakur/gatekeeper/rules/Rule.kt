package com.satyamthakur.gatekeeper.rules

import com.satyamthakur.gatekeeper.model.UserContext

interface Rule {
    fun matches(user: UserContext): Boolean
}