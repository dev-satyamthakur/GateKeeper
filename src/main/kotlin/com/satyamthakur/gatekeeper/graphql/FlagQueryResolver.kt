package com.satyamthakur.gatekeeper.graphql

import com.satyamthakur.gatekeeper.model.FeatureFlag
import com.satyamthakur.gatekeeper.model.UserContext
import com.satyamthakur.gatekeeper.service.FeatureFlagService
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.stereotype.Controller

@Controller
class FlagQueryResolver(
    private val service: FeatureFlagService
) {

    @QueryMapping
    fun flags(): List<FeatureFlag> {
        return service.getAllFlags()
    }

    @QueryMapping
    fun evaluateFlag(
        @Argument flag: String,
        @Argument userId: String,
        @Argument country: String?,
        @Argument device: String?,
        @Argument email: String?
    ): Boolean {

        val user = UserContext(
            userId = userId,
            country = country,
            device = device,
            email = email
        )

        return service.evaluate(flag, user)
    }
}