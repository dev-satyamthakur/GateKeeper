package com.satyamthakur.gatekeeper.events

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class FlagUpdatePublisher(
    private val redisTemplate: StringRedisTemplate
) {

    fun publish(flagName: String) {

        redisTemplate.convertAndSend("flag-updates", flagName)
    }
}