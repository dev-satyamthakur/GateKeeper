package com.satyamthakur.gatekeeper.model

data class UserContext(

    val userId: String,

    val country: String? = null,

    val device: String? = null,

    val email: String? = null
)