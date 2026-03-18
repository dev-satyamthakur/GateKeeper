package com.satyamthakur.gatekeeper.events

import com.satyamthakur.gatekeeper.events.FlagUpdateSubscriber
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer

@Configuration
class RedisConfig {

    @Bean
    fun container(
        connectionFactory: RedisConnectionFactory,
        subscriber: FlagUpdateSubscriber
    ): RedisMessageListenerContainer {

        val container = RedisMessageListenerContainer()

        container.setConnectionFactory(connectionFactory)
        container.addMessageListener(subscriber, PatternTopic("flag-updates"))

        return container
    }
}