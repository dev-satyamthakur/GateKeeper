package com.satyamthakur.gatekeeper.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "feature_flags")
class FeatureFlag(

    @Id
    @GeneratedValue
    val id: UUID? = null,

    @Column(unique = true)
    val name: String,

    val enabled: Boolean,

    val rolloutPercentage: Int,

    val createdAt: LocalDateTime = LocalDateTime.now()
)