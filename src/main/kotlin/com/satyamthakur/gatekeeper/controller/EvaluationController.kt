package com.satyamthakur.gatekeeper.controller

import com.satyamthakur.gatekeeper.model.UserContext
import com.satyamthakur.gatekeeper.service.FeatureFlagService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/evaluate")
class EvaluationController(
    private val service: FeatureFlagService
) {

    @GetMapping
    fun evaluate(
        @RequestParam flag: String,
        @RequestParam userId: String
    ): Boolean {

        val user = UserContext(userId = userId)

        return service.evaluate(flag, user)
    }

}