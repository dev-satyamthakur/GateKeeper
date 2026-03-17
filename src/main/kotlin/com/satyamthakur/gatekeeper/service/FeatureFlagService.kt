package com.satyamthakur.gatekeeper.service

import com.satyamthakur.gatekeeper.evaluation.FeatureEvaluator
import com.satyamthakur.gatekeeper.model.FeatureFlag
import com.satyamthakur.gatekeeper.model.UserContext
import com.satyamthakur.gatekeeper.repository.FeatureFlagRepository
import org.springframework.stereotype.Service
import com.satyamthakur.gatekeeper.cache.RedisCacheService

@Service
class FeatureFlagService(
    private val repository: FeatureFlagRepository,
    private val evaluator: FeatureEvaluator,
    private val cache: RedisCacheService
) {

    fun createFlag(name: String, rollout: Int): FeatureFlag {

        val flag = FeatureFlag(
            name = name,
            enabled = true,
            rolloutPercentage = rollout
        )

        val saved = repository.save(flag)

        // write-through cache
        cache.setFlag(saved)

        return saved
    }

    fun getAllFlags(): List<FeatureFlag> {
        return repository.findAll()
    }

    fun evaluate(flagName: String, user: UserContext): Boolean {

        // 1️⃣ Try cache
        val cachedFlag = cache.getFlag(flagName)

        val flag = if (cachedFlag != null) {
            cachedFlag
        } else {
            // 2️⃣ Fallback to DB
            val dbFlag = repository.findByName(flagName) ?: return false

            // populate cache
            cache.setFlag(dbFlag)

            dbFlag
        }

        return evaluator.isEnabled(flag, user)
    }
}