package com.satyamthakur.gatekeeper.cache

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.satyamthakur.gatekeeper.model.FeatureFlag
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisCacheService(
    private val redisTemplate: StringRedisTemplate
) {
    private val objectMapper = jacksonObjectMapper().findAndRegisterModules()

    fun getFlag(flagName: String): FeatureFlag? {

        val key = "flag:$flagName"

        val value = redisTemplate.opsForValue().get(key) ?: return null

        return objectMapper.readValue(value, FeatureFlag::class.java)
    }

    fun setFlag(flag: FeatureFlag) {

        val key = "flag:${flag.name}"

        val value = objectMapper.writeValueAsString(flag)

        redisTemplate.opsForValue().set(key, value)
    }
}