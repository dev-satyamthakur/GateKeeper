package com.satyamthakur.gatekeeper.service

import com.satyamthakur.gatekeeper.cache.RedisCacheService
import com.satyamthakur.gatekeeper.evaluation.FeatureEvaluator
import com.satyamthakur.gatekeeper.model.FeatureFlag
import com.satyamthakur.gatekeeper.model.UserContext
import com.satyamthakur.gatekeeper.repository.FeatureFlagRepository
import com.satyamthakur.gatekeeper.repository.FlagRuleRepository
import com.satyamthakur.gatekeeper.events.FlagUpdatePublisher
import org.springframework.stereotype.Service

@Service
class FeatureFlagService(
    private val repository: FeatureFlagRepository,
    private val evaluator: FeatureEvaluator,
    private val cache: RedisCacheService,
    private val publisher: FlagUpdatePublisher,
    private val ruleRepository: FlagRuleRepository
) {
    fun createFlag(name: String, rollout: Int): FeatureFlag {
        val flag = FeatureFlag(
            name = name,
            enabled = true,
            rolloutPercentage = rollout
        )

        val saved = repository.save(flag)
        cache.setFlag(saved)

        return saved
    }

    fun getAllFlags(): List<FeatureFlag> {
        return repository.findAll()
    }

    fun evaluate(flagName: String, user: UserContext): Boolean {

        val cachedFlag = cache.getFlag(flagName)
    
        val flag = cachedFlag ?: repository.findByName(flagName)
            ?: return false
    
        val rules = ruleRepository.findByFlagName(flagName)
    
        return evaluator.isEnabled(flag, user, rules)
    }

    fun updateFlag(name: String, enabled: Boolean, rollout: Int): FeatureFlag {
        val flag = repository.findByName(name)
            ?: throw RuntimeException("Flag not found")

        val updated = FeatureFlag(
            id = flag.id,
            name = flag.name,
            enabled = enabled,
            rolloutPercentage = rollout,
            createdAt = flag.createdAt
        )

        val saved = repository.save(updated)
        cache.setFlag(saved)

        // publish the event to the redis channel
        publisher.publish(saved.name)

        return saved
    }
}