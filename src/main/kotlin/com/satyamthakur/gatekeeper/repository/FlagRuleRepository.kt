package com.satyamthakur.gatekeeper.repository

import com.satyamthakur.gatekeeper.model.FlagRule
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FlagRuleRepository : JpaRepository<FlagRule, UUID> {

    fun findByFlagName(flagName: String): List<FlagRule>
}