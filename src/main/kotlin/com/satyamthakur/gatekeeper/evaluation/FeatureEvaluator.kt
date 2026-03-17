package com.satyamthakur.gatekeeper.evaluation

import com.satyamthakur.gatekeeper.model.FeatureFlag
import com.satyamthakur.gatekeeper.model.UserContext
import org.springframework.stereotype.Component
import kotlin.math.abs

@Component
class FeatureEvaluator {

    fun isEnabled(flag: FeatureFlag, user: UserContext): Boolean {

        // Step 1: global switch
        if (!flag.enabled) return false

        // Step 2: deterministic rollout
        val hash = (user.userId + flag.name).hashCode()
        val bucket = abs(hash % 100)

        return bucket < flag.rolloutPercentage
    }
}
