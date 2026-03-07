package com.satyamthakur.gatekeeper.repository

import com.satyamthakur.gatekeeper.model.FeatureFlag
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface FeatureFlagRepository : JpaRepository<FeatureFlag, UUID> {

    fun findByName(name: String): FeatureFlag?

}