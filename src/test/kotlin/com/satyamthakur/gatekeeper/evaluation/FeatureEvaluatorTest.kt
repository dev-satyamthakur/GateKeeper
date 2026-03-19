package com.satyamthakur.gatekeeper.evaluation

import com.satyamthakur.gatekeeper.model.FeatureFlag
import com.satyamthakur.gatekeeper.model.UserContext
import com.satyamthakur.gatekeeper.rules.RuleFactory
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FeatureEvaluatorTest {

    private val evaluator = FeatureEvaluator(RuleFactory())

    @Test
    fun `20 percent rollout should enable feature for around 20 out of 100 users`() {
        val flag = FeatureFlag(
            name = "new_checkout",
            enabled = true,
            rolloutPercentage = 20
        )

        val randomUserIds = (1..10000)
            .shuffled()
            .map { userNumber -> "user-${userNumber}" }

        val enabledCount = randomUserIds
            .count { userId ->
                evaluator.isEnabled(flag, UserContext(userId = userId), emptyList())
            }

        println("Ran $enabledCount times")

        // Deterministic hashing should distribute roughly near the configured rollout.
        assertTrue(
            enabledCount in 1000..3000,
            "Expected around 20 enabled users out of 100 for 20% rollout, but got $enabledCount"
        )
    }
}
