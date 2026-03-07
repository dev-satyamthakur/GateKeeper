package com.satyamthakur.gatekeeper.service

import com.satyamthakur.gatekeeper.evaluation.FeatureEvaluator
import com.satyamthakur.gatekeeper.model.FeatureFlag
import com.satyamthakur.gatekeeper.model.UserContext
import com.satyamthakur.gatekeeper.repository.FeatureFlagRepository
import org.springframework.stereotype.Service

@Service
class FeatureFlagService(
    private val repository: FeatureFlagRepository,
    private val evaluator: FeatureEvaluator
) {

    fun createFlag(name: String, rollout: Int): FeatureFlag {

        val flag = FeatureFlag(
            name = name,
            enabled = true,
            rolloutPercentage = rollout
        )

        return repository.save(flag)
    }

    fun getAllFlags(): List<FeatureFlag> {
        return repository.findAll()
    }

    fun evaluate(flagName: String, user: UserContext): Boolean {
        val flag = repository.findByName(flagName) ?: return false
        return evaluator.isEnabled(flag, user)
    }

}