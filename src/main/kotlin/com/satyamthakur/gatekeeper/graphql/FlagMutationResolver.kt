package com.satyamthakur.gatekeeper.graphql

import com.satyamthakur.gatekeeper.model.FeatureFlag
import com.satyamthakur.gatekeeper.model.FlagRule
import com.satyamthakur.gatekeeper.repository.FlagRuleRepository
import com.satyamthakur.gatekeeper.service.FeatureFlagService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class FlagMutationResolver(
    private val service: FeatureFlagService,
    private val ruleRepository: FlagRuleRepository
) {

    @MutationMapping
    fun createFlag(
        @Argument name: String,
        @Argument rollout: Int
    ): FeatureFlag {
        return service.createFlag(name, rollout)
    }

    @MutationMapping
    fun updateFlag(
        @Argument name: String,
        @Argument enabled: Boolean,
        @Argument rollout: Int
    ): FeatureFlag {
        return service.updateFlag(name, enabled, rollout)
    }

    @MutationMapping
    fun createRule(
        @Argument flagName: String,
        @Argument attribute: String,
        @Argument operator: String,
        @Argument value: String
    ): FlagRule {

        val rule = FlagRule(
            flagName = flagName,
            attribute = attribute,
            operator = operator,
            value = value
        )

        return ruleRepository.save(rule)
    }
}