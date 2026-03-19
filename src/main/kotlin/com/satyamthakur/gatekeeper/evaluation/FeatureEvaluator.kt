package com.satyamthakur.gatekeeper.evaluation

import com.satyamthakur.gatekeeper.model.FeatureFlag
import com.satyamthakur.gatekeeper.model.UserContext
import com.satyamthakur.gatekeeper.model.FlagRule
import com.satyamthakur.gatekeeper.rules.RuleFactory
import org.springframework.stereotype.Component
import kotlin.math.abs

@Component
class FeatureEvaluator(
    private val ruleFactory: RuleFactory
) {
    fun isEnabled(
        flag: FeatureFlag,
        user: UserContext,
        rules: List<FlagRule>
    ): Boolean {

        // 1️⃣ global switch
        if (!flag.enabled) return false

        // 2️⃣ rule evaluation
        for (ruleEntity in rules) {

            val rule = ruleFactory.create(ruleEntity)

            if (rule.matches(user)) {
                return true
            }
        }

        // 3️⃣ fallback rollout
        val hash = (user.userId + flag.name).hashCode()

        val bucket = abs(hash % 100)

        return bucket < flag.rolloutPercentage
    }
}
