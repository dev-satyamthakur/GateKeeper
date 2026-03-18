package com.satyamthakur.gatekeeper.events

import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component

@Component
class FlagUpdateSubscriber : MessageListener {

    override fun onMessage(message: Message, pattern: ByteArray?) {

        val flagName = String(message.body)

        println("Flag updated: $flagName")
    }
}