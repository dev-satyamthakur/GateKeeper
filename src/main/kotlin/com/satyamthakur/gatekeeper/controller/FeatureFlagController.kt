package com.satyamthakur.gatekeeper.controller

import com.satyamthakur.gatekeeper.model.FeatureFlag
import com.satyamthakur.gatekeeper.service.FeatureFlagService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/flags")
class FeatureFlagController(
    private val service: FeatureFlagService
) {

    @PostMapping
    fun createFlag(
        @RequestParam name: String,
        @RequestParam rollout: Int
    ): FeatureFlag {

        return service.createFlag(name, rollout)
    }

    @GetMapping
    fun getFlags(): List<FeatureFlag> {
        return service.getAllFlags()
    }
}