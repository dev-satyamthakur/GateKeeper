package com.satyamthakur.gatekeeper.service

import com.satyamthakur.gatekeeper.model.FeatureFlag
import com.satyamthakur.gatekeeper.repository.FeatureFlagRepository
import org.springframework.stereotype.Service

@Service
class FeatureFlagService(
    private val repository: FeatureFlagRepository
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

}